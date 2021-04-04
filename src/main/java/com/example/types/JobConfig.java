
package com.example.types;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "header",
    "skipHeader",
    "skipTrailer",
    "jsonSchemaClass",
    "avroSchemaClass",
    "inputFilePath",
    "outputFilePath",
    "avroDirectory",
    "avroSchemaPath",
    "srcToTgtMap",
    "standardization"
})
public class JobConfig implements Serializable
{

    @JsonProperty("header")
    private String header;
    @JsonProperty("skipHeader")
    private Boolean skipHeader;
    @JsonProperty("skipTrailer")
    private Boolean skipTrailer;
    @JsonProperty("jsonSchemaClass")
    private String jsonSchemaClass;
    @JsonProperty("avroSchemaClass")
    private String avroSchemaClass;
    @JsonProperty("inputFilePath")
    private String inputFilePath;
    @JsonProperty("outputFilePath")
    private String outputFilePath;
    @JsonProperty("avroDirectory")
    private String avroDirectory;
    @JsonProperty("avroSchemaPath")
    private String avroSchemaPath;
    @JsonProperty("srcToTgtMap")
    private String srcToTgtMap;
    @JsonProperty("standardization")
    private Standardization standardization;

    @JsonProperty("header")
    public String getHeader() {
        return header;
    }

    @JsonProperty("header")
    public void setHeader(String header) {
        this.header = header;
    }

    public JobConfig withHeader(String header) {
        this.header = header;
        return this;
    }

    @JsonProperty("skipHeader")
    public Boolean getSkipHeader() {
        return skipHeader;
    }

    @JsonProperty("skipHeader")
    public void setSkipHeader(Boolean skipHeader) {
        this.skipHeader = skipHeader;
    }

    public JobConfig withSkipHeader(Boolean skipHeader) {
        this.skipHeader = skipHeader;
        return this;
    }

    @JsonProperty("skipTrailer")
    public Boolean getSkipTrailer() {
        return skipTrailer;
    }

    @JsonProperty("skipTrailer")
    public void setSkipTrailer(Boolean skipTrailer) {
        this.skipTrailer = skipTrailer;
    }

    public JobConfig withSkipTrailer(Boolean skipTrailer) {
        this.skipTrailer = skipTrailer;
        return this;
    }

    @JsonProperty("jsonSchemaClass")
    public String getJsonSchemaClass() {
        return jsonSchemaClass;
    }

    @JsonProperty("jsonSchemaClass")
    public void setJsonSchemaClass(String jsonSchemaClass) {
        this.jsonSchemaClass = jsonSchemaClass;
    }

    public JobConfig withJsonSchemaClass(String jsonSchemaClass) {
        this.jsonSchemaClass = jsonSchemaClass;
        return this;
    }

    @JsonProperty("avroSchemaClass")
    public String getAvroSchemaClass() {
        return avroSchemaClass;
    }

    @JsonProperty("avroSchemaClass")
    public void setAvroSchemaClass(String avroSchemaClass) {
        this.avroSchemaClass = avroSchemaClass;
    }

    public JobConfig withAvroSchemaClass(String avroSchemaClass) {
        this.avroSchemaClass = avroSchemaClass;
        return this;
    }

    @JsonProperty("inputFilePath")
    public String getInputFilePath() {
        return inputFilePath;
    }

    @JsonProperty("inputFilePath")
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public JobConfig withInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        return this;
    }

    @JsonProperty("outputFilePath")
    public String getOutputFilePath() {
        return outputFilePath;
    }

    @JsonProperty("outputFilePath")
    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public JobConfig withOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        return this;
    }

    @JsonProperty("avroDirectory")
    public String getAvroDirectory() {
        return avroDirectory;
    }

    @JsonProperty("avroDirectory")
    public void setAvroDirectory(String avroDirectory) {
        this.avroDirectory = avroDirectory;
    }

    public JobConfig withAvroDirectory(String avroDirectory) {
        this.avroDirectory = avroDirectory;
        return this;
    }

    @JsonProperty("avroSchemaPath")
    public String getAvroSchemaPath() {
        return avroSchemaPath;
    }

    @JsonProperty("avroSchemaPath")
    public void setAvroSchemaPath(String avroSchemaPath) {
        this.avroSchemaPath = avroSchemaPath;
    }

    public JobConfig withAvroSchemaPath(String avroSchemaPath) {
        this.avroSchemaPath = avroSchemaPath;
        return this;
    }

    @JsonProperty("srcToTgtMap")
    public String getSrcToTgtMap() {
        return srcToTgtMap;
    }

    @JsonProperty("srcToTgtMap")
    public void setSrcToTgtMap(String srcToTgtMap) {
        this.srcToTgtMap = srcToTgtMap;
    }

    public JobConfig withSrcToTgtMap(String srcToTgtMap) {
        this.srcToTgtMap = srcToTgtMap;
        return this;
    }

    @JsonProperty("standardization")
    public Standardization getStandardization() {
        return standardization;
    }

    @JsonProperty("standardization")
    public void setStandardization(Standardization standardization) {
        this.standardization = standardization;
    }

    public JobConfig withStandardization(Standardization standardization) {
        this.standardization = standardization;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("header", header).append("skipHeader", skipHeader).append("skipTrailer", skipTrailer).append("jsonSchemaClass", jsonSchemaClass).append("avroSchemaClass", avroSchemaClass).append("inputFilePath", inputFilePath).append("outputFilePath", outputFilePath).append("avroDirectory", avroDirectory).append("avroSchemaPath", avroSchemaPath).append("srcToTgtMap", srcToTgtMap).append("standardization", standardization).toString();
    }

}
