package com.example.dataflow.builder;

import org.apache.avro.generic.GenericRecord;
import org.json.JSONObject;

import java.util.Map;

/**
 * This is an interface which will be implemented by the specifc feed Avro record
 * builder
 * @author
 * @Version
 */
public interface AvroBuilder {

    /**
     * An abstract method to generate generic record
     *
     * @param schema
     * @param recordMap
     * @param trgtToSrcMap
     * @return
     */
    GenericRecord build(String schema, JSONObject recordMap,
                                     Map<String,String> trgtToSrcMap);
}
