package com.example.dataflow.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.beam.sdk.io.FileSystems;
import org.json.JSONArray;
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
}
