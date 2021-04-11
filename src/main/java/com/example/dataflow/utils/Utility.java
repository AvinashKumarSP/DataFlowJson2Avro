package com.example.dataflow.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.io.FileSystems;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public interface Utility {

    Logger LOG = LoggerFactory.getLogger(Utility.class);

    static String readFile(String jsonConfigFile) throws IOException {
        ReadableByteChannel channel = FileSystems.open(FileSystems.matchNewResource(
                jsonConfigFile, false));

        try (InputStream stream = Channels.newInputStream(channel)) {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder dataBuilder = new StringBuilder();

            String line;
            while ((line = streamReader.readLine()) != null) {
                dataBuilder.append(line);
            }

            return dataBuilder.toString();
        }

    }

    static HashMap<String, String> getSrcToTgtMap(String srcToTgtMapInput) throws IOException {
        HashMap<String, String> srcToTgtMapOutput = new HashMap<>();
        String[] srcToTgtArr = srcToTgtMapInput.split(",");
        for (String srcToTgt : srcToTgtArr) {
            srcToTgtMapOutput.put(srcToTgt.split(":")[0], srcToTgt.split(":")[1]);
        }
        return srcToTgtMapOutput;

    }

    static boolean jsonValidator(String value, String jsonSchemaClass) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            objMapper.readValue(value, Class.forName(jsonSchemaClass));
            return true;
        } catch (ClassNotFoundException | IOException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    static boolean jsonValidatorV1(String jsonSchema, String jsonString) {

        try{
            System.out.println("Inside Validatorv1");
            String schemaString = readFile(jsonSchema);
            JSONObject schema = new JSONObject(schemaString);
            JSONObject value = new JSONObject(jsonString);
            System.out.println("Inside Validatorv1");
            org.everit.json.schema.Schema schema_ = SchemaLoader.load(schema);
            schema_.validate(value);
            return true;
        }catch (ValidationException ex){
            System.out.println(ex);
            ex.getCausingExceptions().stream()
                    .map(ValidationException::getMessage)
                    .forEach(System.out::println);
            return false;

        }catch (IOException ex){
            System.out.println(ex);
            return false;

        }

    }

    static ArrayList<Object> jsonArrToList(JSONArray jsonArray){
        ArrayList<Object> objectArrayList = new ArrayList<Object>();
        //Checking whether the JSON array has some value or not
        if (jsonArray != null) {
            //Iterating JSON array
            for (int i=0;i<jsonArray.length();i++){
                //Adding each element of JSON array into ArrayList
                objectArrayList.add(jsonArray.get(i));
            }
        }
        return objectArrayList;
    }


    static GenericRecord avroBuilder(String avroSchema, JSONObject recordMap,
                               Map<String,String> tgtToSrcMap) {
        Schema schema = new Schema.Parser().parse(avroSchema);
        GenericRecord genericRecord = new GenericData.Record(schema);
        for (Schema.Field field : schema.getFields()) {
            String fieldName = field.name();
            String fieldType = field.schema().getType().getName().toLowerCase();
            if(fieldType.equals("array")){
                JSONArray jsonArray = recordMap.getJSONArray(tgtToSrcMap.get("ItemsCostList"));
                ArrayList<Object> objList = jsonArrToList(jsonArray);
                genericRecord.put(fieldName, objList);
            }else{
                genericRecord.put(fieldName, recordMap.get(tgtToSrcMap.get(fieldName)));
            }
        }

        return genericRecord;
    }
}
