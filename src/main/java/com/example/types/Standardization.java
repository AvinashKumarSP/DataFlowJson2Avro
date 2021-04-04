
package com.example.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dateUtc",
    "stringToInteger",
    "dataMask",
    "stringArrToIntegerArr"
})
public class Standardization implements Serializable
{

    @JsonProperty("dateUtc")
    private List<String> dateUtc = new ArrayList<String>();
    @JsonProperty("stringToInteger")
    private List<String> stringToInteger = new ArrayList<String>();
    @JsonProperty("dataMask")
    private List<String> dataMask = new ArrayList<String>();
    @JsonProperty("stringArrToIntegerArr")
    private List<String> stringArrToIntegerArr = new ArrayList<String>();

    @JsonProperty("dateUtc")
    public List<String> getDateUtc() {
        return dateUtc;
    }

    @JsonProperty("dateUtc")
    public void setDateUtc(List<String> dateUtc) {
        this.dateUtc = dateUtc;
    }

    public Standardization withDateUtc(List<String> dateUtc) {
        this.dateUtc = dateUtc;
        return this;
    }

    @JsonProperty("stringToInteger")
    public List<String> getStringToInteger() {
        return stringToInteger;
    }

    @JsonProperty("stringToInteger")
    public void setStringToInteger(List<String> stringToInteger) {
        this.stringToInteger = stringToInteger;
    }

    public Standardization withStringToInteger(List<String> stringToInteger) {
        this.stringToInteger = stringToInteger;
        return this;
    }

    @JsonProperty("dataMask")
    public List<String> getDataMask() {
        return dataMask;
    }

    @JsonProperty("dataMask")
    public void setDataMask(List<String> dataMask) {
        this.dataMask = dataMask;
    }

    public Standardization withDataMask(List<String> dataMask) {
        this.dataMask = dataMask;
        return this;
    }

    @JsonProperty("stringArrToIntegerArr")
    public List<String> getStringArrToIntegerArr() {
        return stringArrToIntegerArr;
    }

    @JsonProperty("stringArrToIntegerArr")
    public void setStringArrToIntegerArr(List<String> stringArrToIntegerArr) {
        this.stringArrToIntegerArr = stringArrToIntegerArr;
    }

    public Standardization withStringArrToIntegerArr(List<String> stringArrToIntegerArr) {
        this.stringArrToIntegerArr = stringArrToIntegerArr;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dateUtc", dateUtc).append("stringToInteger", stringToInteger).append("dataMask", dataMask).append("stringArrToIntegerArr", stringArrToIntegerArr).toString();
    }

}
