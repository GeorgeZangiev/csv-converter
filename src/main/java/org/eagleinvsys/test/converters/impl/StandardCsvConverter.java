package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;
import org.eagleinvsys.test.converters.StandardConverter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class StandardCsvConverter implements StandardConverter {

    private CsvConverter csvConverter;

    public StandardCsvConverter(CsvConverter csvConverter) {
        this.csvConverter = csvConverter;
    }

    /**
     * Converts given {@link List<Map>} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format. All maps must have the same set of keys
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(List<Map<String, String>> collectionToConvert, OutputStream outputStream) throws IOException {
        ConvertibleCollection collection = new ConvertibleCollection() {
            @Override
            public Collection<String> getHeaders() {
                return collectionToConvert.get(0).keySet();
            }
            @Override
            public Iterable<ConvertibleMessage> getRecords() {
                List<ConvertibleMessage> result = new ArrayList<>();
                for (Map message : collectionToConvert) {
                    HashMapMessage msg = new HashMapMessage();
                    msg.setMap(message);
                    result.add(msg);
                }
                return result;
            }
        };
        csvConverter.convert(collection, outputStream);
    }

}