package com.example.dataflow.transformer;

import com.example.dataflow.builder.AvroBuilder;
import com.example.dataflow.builder.BuilderFactory;
import com.example.dataflow.utils.DataPreProcess;
import com.example.dataflow.utils.Utility;

import com.example.types.Standardization;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.transforms.DoFn;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * JsonToAvroFn is an extension of DoFn which basically convert
 * each string record into generic record
 *
 *
 */
public class JsonToAvroFn extends DoFn<String, GenericRecord> implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToAvroFn.class);

    private Standardization standardization;
    private String jsonSchemaClass;
    private DataPreProcess dataPreProcess;
    private HashMap<String,String> srcToTgtMap;
    private HashMap<String, String> tgtToSrcMap;
    private String avroSchema;
    private String avroSchemaClass;

    public JsonToAvroFn(DataPreProcess dataPreProcess,
                            HashMap<String,String> srcToTgtMap,
                            String jsonSchemaClass, String avroSchema,
                            String avroSchemaClass) {

        this.dataPreProcess = dataPreProcess;
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

    }

    /**
     * 1. Validates the input json string with respective feed specfic json schema
     * 2. Execute the standardization rules to each attribute and generate a hash map
     *
     * @param ctx
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @DoFn.ProcessElement
    public void processElement(ProcessContext ctx) throws IllegalArgumentException, IOException, ClassNotFoundException {
        System.out.println("eeeeeeeee");
        boolean isVaildJson = Utility.jsonValidator(ctx.element(),jsonSchemaClass);
        if (isVaildJson){
            System.out.println("eeeeeeeee11111");
            JSONObject jsonObject = new JSONObject(ctx.element());
            ObjectMapper objectMapper = new ObjectMapper();

            //Store source field name as key and value as standardized value
            HashMap<String,Object> recordMap = objectMapper.readValue(ctx.element(), new TypeReference<Map<String, Object>>(){});
            //HashMap<String,Object> recordMap = new HashMap<>();
            //Pass source field name and its respective value to Standardize
            for (Map.Entry<String, String> entry : srcToTgtMap.entrySet()) {
                String key = entry.getKey();
                System.out.println("Tessssss111"+jsonObject.get(key));
                Object value = dataPreProcess.invokeStandardizer(key, jsonObject.get(key));
                jsonObject.put(key,value);
            }

            AvroBuilder avroBuilder = BuilderFactory.getAvroBuilder(avroSchemaClass).get();
            System.out.println("vaaa"+jsonObject);
            ObjectMapper mapper = new ObjectMapper();

            GenericRecord genericRecord = avroBuilder.build(avroSchema, jsonObject, tgtToSrcMap);
            System.out.println("vaaa"+jsonObject);
            //
            //objmapp.readValue(jsonObject.toString(),Class.forName(jsonSchemaClass));
            System.out.println(genericRecord.toString());
            ctx.output(genericRecord);
        }

    }
}