package com.example.dataflow.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static com.example.dataflow.utils.Utility.jsonArrToList;

/**
 *
 * This is PosInvoice feed specific builder class which implemented the AvroBuilder
 *
 * @author
 * @version
 */
public class PosInvoiceBuilder implements AvroBuilder{

    private static final Logger LOG = LoggerFactory.getLogger(PosInvoiceBuilder.class);

    /**
     * Build method is to build the generic record based on the input schema
     * @param avroSchema
     * @param recordMap
     * @param tgtToSrcMap
     * @return GenericRecord
     */
    @Override
    public GenericRecord build(String avroSchema, JSONObject recordMap,
                                     Map<String,String> tgtToSrcMap) {
        Schema schema = new Schema.Parser().parse(avroSchema);
        GenericRecord genericRecord = new GenericData.Record(schema);
        ObjectMapper objMapper = new ObjectMapper();
        JSONArray jsonArray = recordMap.getJSONArray(tgtToSrcMap.get("ItemsCostList"));
        ArrayList<Object> itemsCostList = jsonArrToList(jsonArray);
        genericRecord.put("PosID", recordMap.get(tgtToSrcMap.get("PosID")));
        genericRecord.put("CustomerType",recordMap.get(tgtToSrcMap.get("CustomerType")));
        genericRecord.put("DateTime",recordMap.get(tgtToSrcMap.get("DateTime")));
        genericRecord.put("DeliveryType",recordMap.get(tgtToSrcMap.get("DeliveryType")));
        genericRecord.put("StoreID",recordMap.get(tgtToSrcMap.get("StoreID")));
        genericRecord.put("ItemsCostList", itemsCostList);
        genericRecord.put("NumberOfItems",recordMap.get(tgtToSrcMap.get("NumberOfItems")));

        return genericRecord;
    }
}
