package com.example.dataflow.transformer;

import com.example.dataflow.utils.Standardizer;
import com.example.dataflow.utils.Utility;
import com.example.types.Standardization;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.TupleTag;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * JsonToAvroFn is an extension of DoFn which basically convert
 * each string record into generic record
 *
 *
 */
public class JsonToAvroFnV1 extends DoFn<String, GenericRecord> implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToAvroFnV1.class);

    private String feedName;
    private Standardization standardization;
    private String jsonSchemaClass;
    private Standardizer standardizer;
    private HashMap<String,String> srcToTgtMap;
    private HashMap<String, String> tgtToSrcMap;
    private String avroSchema;
    private String avroSchemaClass;
    private String jsonSchemaPath;
    public static TupleTag<GenericRecord> validTag = new TupleTag<GenericRecord>(){};
    public static TupleTag<Failure> failuresTag = new TupleTag<Failure>(){};

    public JsonToAvroFnV1(String feedName, Standardizer standardizer,
                          HashMap<String,String> srcToTgtMap,
                          String jsonSchemaClass, String avroSchema,
                          String avroSchemaClass, String jsonSchemaPath) {

        this.feedName = feedName;
        this.standardizer = standardizer;
        this.srcToTgtMap = srcToTgtMap;

        HashMap<String, String> tgtToSrcMap = new HashMap<>();
        for(Map.Entry<String, String> entry : srcToTgtMap.entrySet()){
            tgtToSrcMap.put(entry.getValue(), entry.getKey());
        }
        //Reverse key as value and value as key
        this.tgtToSrcMap = tgtToSrcMap;
        this.jsonSchemaClass = jsonSchemaClass;
        this.avroSchema = avroSchema;
        this.avroSchemaClass = avroSchemaClass;
        this.jsonSchemaPath = jsonSchemaPath;

    }

    /**
     * 1. Validates the input json string with respective feed specfic json schema
     * 2. Execute the standardization rules to each attribute and generate a hash map
     *
     * @param ctx
     * @throws IllegalArgumentException
     */
    @ProcessElement
    public void processElement(ProcessContext ctx) throws IllegalArgumentException {

        String jsonString = ctx.element();
        try{
            boolean isVaildJson = Utility.jsonValidatorV1(jsonSchemaPath,jsonString);
            if (isVaildJson){

                JSONObject jsonObject = new JSONObject(jsonString);
                //HashMap<String,Object> recordMap = new HashMap<>();
                //Pass source field name and its respective value to Standardize

                JSONObject stdJsonObject = standardizer.invokeStandardizer(jsonObject);

                GenericRecord genericRecord = Utility.avroBuilder(avroSchema, stdJsonObject, tgtToSrcMap);
                System.out.println("vaaa"+jsonObject);
                //
                //objmapp.readValue(jsonObject.toString(),Class.forName(jsonSchemaClass));
                System.out.println(genericRecord.toString());
                ctx.output(validTag,genericRecord);
            }
        } catch (Throwable throwable){
            Failure failure = new Failure(feedName,jsonString,throwable);
            ctx.output(failuresTag, failure);
        }

    }
}