package com.example.dataflow.builder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * This is PosInvoice feed specific builder class which implemented the AvroBuilder
 *
 * @author
 * @version
 */
public class PosInvoiceBuilder implements AvroBuilder{

    private static final Logger LOG = LoggerFactory.getLogger(PosInvoiceBuilder.class);

    private String avroSchema;
    private Map<String,Object> recordMap;
    private Map<String,String> tgtToSrcMap;

    /**
     * Build method is to build the generic record based on the input schema
     * @param avroSchema
     * @param recordMap
     * @param tgtToSrcMap
     * @return GenericRecord
     */
    @Override
    public GenericRecord build(String avroSchema, Map<String,Object> recordMap,
                                     Map<String,String> tgtToSrcMap) {
        Schema schema = new Schema.Parser().parse(avroSchema);
        GenericRecord genericRecord = new GenericData.Record(schema);
        genericRecord.put("PosID", recordMap.get(tgtToSrcMap.get("PosID")));
        genericRecord.put("CustomerType",recordMap.get(tgtToSrcMap.get("CustomerType")));
        genericRecord.put("DateTime",recordMap.get(tgtToSrcMap.get("DateTime")));
        genericRecord.put("DeliveryType",recordMap.get(tgtToSrcMap.get("DeliveryType")));
        genericRecord.put("StoreID",recordMap.get(tgtToSrcMap.get("StoreID")));
        genericRecord.put("ItemsCostList",recordMap.get(tgtToSrcMap.get("ItemsCostList")));
        genericRecord.put("NumberOfItems",recordMap.get(tgtToSrcMap.get("NumberOfItems")));

        return genericRecord;
    }
}
