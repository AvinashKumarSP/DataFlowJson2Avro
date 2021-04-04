package com.example.dataflow.config;

import com.example.dataflow.pipeline.JsonToAvroConverter;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;

/**
 * Options supported by {@link JsonToAvroConverter}.
 *
 * <p>Defining your own configuration options. Here, you can add your own arguments to
 * be processed by the command-line parser, and specify default values for them. You can then
 * access the options values in your pipeline code.
 *
 * <p>Inherits standard configuration options.
 */
public interface CustomOptions extends PipelineOptions {

    /** Set jobConfig required parameter to specify Job Json config file name. A local path and Google
     * Cloud Storage path are supported */
    @Description("Job execution json config file")
    @Validation.Required
    String getJobConfigFile();
    void setJobConfigFile(String value);

}
