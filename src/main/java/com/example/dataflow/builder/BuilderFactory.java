package com.example.dataflow.builder;

import com.example.dataflow.pipeline.JsonToAvroConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.dataflow.utils.Constants.POS_INVOICE;

/**
 * BuilderFactory class is a factory class where all the feed specific
 * avro builder objects are registered.
 *
 * @author
 * @version
 */
public class BuilderFactory {

    private static final Logger LOG = LoggerFactory.getLogger(BuilderFactory.class);

    static Map<String, AvroBuilder> builderMap = new HashMap<>();
    static {
        builderMap.put(POS_INVOICE, new PosInvoiceBuilder());
        // more operators
    }

    /**
     * This is a getter method to get the avro builder for a specific feed
     * @param builder
     * @return Optional of AvroBuilder
     */
    public static Optional<AvroBuilder> getAvroBuilder(String builder) {
        return Optional.ofNullable(builderMap.get(builder));
    }
}