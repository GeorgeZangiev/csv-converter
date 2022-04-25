package org.eagleinvsys.test.converters;

import com.opencsv.CSVReader;
import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.*;

class StandardCsvConverterTests {

    @Mock
    private CsvConverter csvConverter;
    private StandardCsvConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new StandardCsvConverter(csvConverter);
    }

    @Test
    void returnsCorrectRepresentation() throws IOException {

        // given
        List<Map<String, String>> collectionToConvert = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("Gender", "F");
        map1.put("Name", "Alice");
        Map<String, String> map2 = new HashMap<>();
        map2.put("Gender", "M");
        map2.put("Name", "Alexander");
        collectionToConvert.add(map1);
        collectionToConvert.add(map2);
        OutputStream outputStream = new FileOutputStream("/Users/elmo/Desktop/test.csv");
        List<String> representation = new ArrayList<>();

        // when
        underTest.convert(collectionToConvert, outputStream);
        CSVReader reader = null;
        try
        {
            //parsing a CSV file into CSVReader class constructor
            reader = new CSVReader(new FileReader("/Users/elmo/Desktop/test.csv"));
            String [] nextLine;
            //reads one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                for(String token : nextLine)
                {
                    representation.add(token);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // then
        List<String> expected = List.of("Gender", "Name", "F", "Alice", "M", "Alexander");
        assertEquals(expected, representation);

    }
}