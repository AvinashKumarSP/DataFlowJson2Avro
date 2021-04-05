package com.example.dataflow.utils;

import com.example.types.Standardization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DataPreProcess implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DataPreProcess.class);

    private Standardization standardization;
    private List<String> dataMask;
    private List<String> dateUtc;
    private List<String> stringToInteger;
    private List<String> stringArrToIntegerArr;

    public DataPreProcess(Standardization standardization){
        this.standardization=standardization;
        this.dataMask=standardization.getDataMask();
        this.dateUtc=standardization.getDateUtc();
        this.stringToInteger=standardization.getStringToInteger();
        this.stringArrToIntegerArr=standardization.getStringArrToIntegerArr();

    }

    public static boolean isStandardizationRequired(){
        return false;
    }

    public static boolean isDataMaskRequired(){
        return false;
    }

    public Object invokeStandardizer(String srcField, Object value){

        Object strToIntResult = StringToInteger(srcField, value);
        Object dataMaskResult = DataMask(srcField, strToIntResult);
        Object dateUtcResult = DateUtc(srcField,dataMaskResult);

        return dateUtcResult;
    }

    public Object StringToInteger(String srcField, Object value) throws NumberFormatException{

        if (stringToInteger.contains(srcField)){
            return Integer.parseInt((String)value);
        }else{
            return value;
        }

    }

    public Object DateUtc(String srcField, Object value) throws DateTimeParseException {


        if (dateUtc.contains(srcField)){

            OffsetDateTime parsedDateTime = OffsetDateTime.parse(value.toString());
            ZonedDateTime dateTimeInMyTimeZone
                    = parsedDateTime.atZoneSameInstant(ZoneId.systemDefault());
            System.out.println("Teeeeeeee"+dateTimeInMyTimeZone.toInstant().toEpochMilli());
            return dateTimeInMyTimeZone.toInstant().toEpochMilli();
        }else{
            return value;
        }

    }

    public Object DataMask(String srcField, Object value) throws NumberFormatException{

        if (dataMask.contains(srcField)){
            System.out.println("Data Masking exists");
            return value;
        }else{
            return value;
        }

    }
}
