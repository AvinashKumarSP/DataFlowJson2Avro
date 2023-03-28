package com.example.dataflow.transformer;

import org.apache.beam.sdk.transforms.DoFn;

import java.util.Arrays;

public class Failure extends DoFn {

    private String feedName;
    private String rawRecord;
    private String errorMessage;
    private String stackTrace;

    public Failure(String feedName, String rawRecord,
                   Throwable thrown) {
        this.feedName = feedName;
        this.rawRecord = rawRecord;
        this.errorMessage = thrown.toString();
        this.stackTrace = Arrays.toString(thrown.getStackTrace());
    }

    @Override
    public String toString() {
        return "{" +
                "\nfeedName: " + this.feedName +
                "\nrawRecord: " + this.rawRecord +
                "\nerrorMessage: " + this.errorMessage +
                "\nstackTrace: " + this.stackTrace +
                "\n}";
    }

}
