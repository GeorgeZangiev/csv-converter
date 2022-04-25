package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */


    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String key : collectionToConvert.getHeaders()) {
            if (!key.equals(",")) sb.append(key);
            else throw new IllegalArgumentException(", is not allowed as key");
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('\n');

        List<ConvertibleMessage> records = (List<ConvertibleMessage>) collectionToConvert.getRecords();
        for (ConvertibleMessage mapMessage : records) {
            for (String key : collectionToConvert.getHeaders()) {
                String msg = mapMessage.getElement(key);
                sb.append(msg);
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }
        outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        System.out.println("done!");
    }

}