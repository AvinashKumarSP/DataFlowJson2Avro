package com.example.dataflow.pipeline;

import java.io.IOException;
import java.util.HashMap;

import com.example.dataflow.config.CustomOptions;
import com.example.dataflow.transformer.JsonToAvroFn;
import com.example.dataflow.utils.DataPreProcess;
import com.example.dataflow.utils.Utility;
import com.example.types.JobConfig;
import com.example.types.Standardization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.generic.GenericRecord;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.io.AvroIO;
import org.apache.beam.sdk.io.FileSystems;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.*;
import org.apache.beam.sdk.transforms.ParDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>This class, {@link JsonToAvroConverter}, is to read the jsonl input file,
 * standardize/obfuscate each record according to the rules specified in the job
 * config file, finally convert the record to avro and write the avro data to the
 * output path
 *
 * <p>Basic concepts, also in the MinimalWordCount example: Reading text files; counting a
 * PCollection; writing to text files
 *
 * <p>Concepts:
 *
 * <pre>
 *   1. Executing a Pipeline both locally and using the selected runner
 *   2. Reading JsonL input file - PCollection of String
 *   3. Using ParDo with DoFns defined to convert Json to Avro - PCollection of GenericRecord
 *   4. Write the Avro output file
 *   5. Defining custom pipeline options to read the json job config file
 * </pre>
 *
 * <p>you can execute this pipeline either locally or using by selecting another runner.
 * <p>To change the runner, specify:
 *
 * <pre>{@code
 * --runner=YOUR_SELECTED_RUNNER
 * }</pre>
 *
 * @author
 * @version
 */
public class JsonToAvroConverter {

    private static final Logger LOG = LoggerFactory.getLogger(JsonToAvroConverter.class);

    /**
     * runJsonToAvro method is to read the jsonl file, standardize, convert to avro and
     * finally write the data to output path
     *
     * @param options
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static void runJsonToAvro(CustomOptions options)
            throws IOException, IllegalArgumentException {
        FileSystems.setDefaultPipelineOptions(options);
        String jsonConfig = Utility.readFile(options.getJobConfigFile());
        ObjectMapper objMapper = new ObjectMapper();
        JobConfig jobConfig = objMapper.readValue(jsonConfig, JobConfig.class);
        //Standardize object initialization
        Standardization standardization = jobConfig.getStandardization();
        DataPreProcess dataPreProcess = new DataPreProcess(standardization);
        String jsonSchemaClass = jobConfig.getJsonSchemaClass();
        String avroSchema = Utility.readFile(jobConfig.getAvroSchemaPath());
        HashMap<String,String> srcToTgtMap = Utility.getSrcToTgtMap(jobConfig.getSrcToTgtMap());
        String avroSchemaClass = jobConfig.getAvroSchemaClass();
        Schema avroSchemaObj = new Schema.Parser().parse(avroSchema);

        // Create the Pipeline object with the options we defined above.
        Pipeline pipeline = Pipeline.create(options);
        System.out.println("Before starting pipeline");
        // Convert CSV to Avro
        pipeline.apply("Read Json files", TextIO.read().from(jobConfig.getInputFilePath()))
                .apply("Convert Json to Avro formatted data",
                        ParDo.of(new JsonToAvroFn(dataPreProcess, srcToTgtMap,
                                jsonSchemaClass, avroSchema, avroSchemaClass)))
                .setCoder(AvroCoder.of(GenericRecord.class, avroSchemaObj))
                .apply("Write Avro formatted data", AvroIO.writeGenericRecords(avroSchema)
                        .to(jobConfig.getOutputFilePath()).withCodec(CodecFactory.snappyCodec()).withSuffix(".avro"));

        // Run the pipeline.
        pipeline.run().waitUntilFinish();
    }

    /**
     * JsonToAvroConverter main method
     * @param args
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        // Create and set your PipelineOptions.
        CustomOptions options =
                PipelineOptionsFactory.fromArgs(args).withValidation().as(CustomOptions.class);
        System.out.println("Heloooooooo");
        //Execute the pipeline to convert json to avro
        runJsonToAvro(options);
    }
}