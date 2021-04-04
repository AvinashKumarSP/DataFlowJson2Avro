package com.example;


import org.apache.beam.sdk.io.FileSystems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) throws URISyntaxException, IOException {

        String schemaPath = Paths.get(ClassLoader.getSystemResource("PosInvoic.avsc").toURI()).toString();
        String avroSchema = getSchema(schemaPath);
        System.out.println(avroSchema);
    }

    public static String getSchema(String schemaPath) throws IOException {
        ReadableByteChannel chan = FileSystems.open(FileSystems.matchNewResource(
                schemaPath, false));

        try (InputStream stream = Channels.newInputStream(chan)) {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder dataBuilder = new StringBuilder();

            String line;
            while ((line = streamReader.readLine()) != null) {
                dataBuilder.append(line);
            }

            return dataBuilder.toString();
        }
    }
}
