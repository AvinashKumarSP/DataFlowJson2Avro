package com.example.dataflow.utils;

import com.example.types.Standardization;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Standardizer implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Standardizer.class);

    private Standardization standardization;
    private List<String> dataMaskFields;
    private List<String> dateUtcFields;
    private List<String> strToIntFields;
    private List<String> strArrToIntArrFields;

    public Standardizer(Standardization standardization){
        this.standardization=standardization;
        this.dataMaskFields=standardization.getDataMask();
        this.dateUtcFields=standardization.getDateUtc();
        this.strToIntFields=standardization.getStringToInteger();
        this.strArrToIntArrFields=standardization.getStringArrToIntegerArr();

    }


    public JSONObject invokeStandardizer(JSONObject jsonObject){

        JSONObject strToIntResult = StringToInteger(jsonObject);
        JSONObject dataMaskResult = DataMask(strToIntResult);
        JSONObject dateUtcResult = DateUtc(dataMaskResult);

        return dateUtcResult;
    }

    public JSONObject StringToInteger(JSONObject jsonObject) throws NumberFormatException{

        for (String field : strToIntFields){
            int result = Integer.parseInt(jsonObject.get(field).toString());
            jsonObject.put(field,result);
        }
        return jsonObject;
    }

    public JSONObject DateUtc(JSONObject jsonObject) throws DateTimeParseException {

        for (String field : dateUtcFields){

            OffsetDateTime parsedDateTime = OffsetDateTime.parse(jsonObject.get(field).toString());
            ZonedDateTime dateTimeInMyTimeZone
                    = parsedDateTime.atZoneSameInstant(ZoneId.systemDefault());
            long result = dateTimeInMyTimeZone.toInstant().toEpochMilli();
            jsonObject.put(field,result);
        }

        return jsonObject;

    }

    public JSONObject DataMask(JSONObject jsonObject) throws NumberFormatException{

        for(String field : dataMaskFields){
            System.out.println("Data Masking exists");
        }

        return jsonObject;

    }
}
