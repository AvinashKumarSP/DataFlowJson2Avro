package com.example.dataflow.utils;

import com.example.types.Standardization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.Serializable;
import java.util.List;

public class DataPreProcess implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(DataPreProcess.class);

    private Standardization standardization;
    private List<String> dataMask;
    private List<String> stringToInteger;
    private List<String> stringArrToIntegerArr;

    public DataPreProcess(Standardization standardization){
        this.standardization=standardization;
        this.dataMask=standardization.getDataMask();
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

        return dataMaskResult;
    }

    public Object StringToInteger(String srcField, Object value) throws NumberFormatException{

        if (stringToInteger.contains(value.toString())){
            return Integer.parseInt((String)value);
        }else{
            return value;
        }

    }

    public Object DateUtc(String srcField, Object value) throws NumberFormatException{

        if (stringToInteger.contains(value.toString())){
            return Integer.parseInt((String)value);
        }else{
            return value;
        }

    }

    public Object DataMask(String srcField, Object value) throws NumberFormatException{

        if (dataMask.contains(value.toString())){
            System.out.println("Data Masking exists");
            return value;
        }else{
            return value;
        }

    }
}
