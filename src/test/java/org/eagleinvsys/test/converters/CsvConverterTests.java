package org.eagleinvsys.test.converters;

import org.eagleinvsys.test.converters.impl.CsvConverter;
import org.eagleinvsys.test.converters.impl.StandardCsvConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CsvConverterTests {

    @Mock
    private CsvConverter csvConverter;
    private StandardCsvConverter underTest;

    @BeforeEach
    void setUp() {
        underTest = new StandardCsvConverter(csvConverter);
    }

    @Test
    void thisShouldThrowIllegalArgumentException() throws FileNotFoundException {
        //given
        List<Map<String, String>> collectionToConvert = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put(",", "F");
        map1.put("Name", "Alice");
        Map<String, String> map2 = new HashMap<>();
        map2.put(",", "M");
        map2.put("Name", "Alexander");
        collectionToConvert.add(map1);
        collectionToConvert.add(map2);
        OutputStream outputStream = new FileOutputStream("/Users/elmo/Desktop/test.csv");
        //then
        assertThrows(IllegalArgumentException.class, () -> underTest.convert(collectionToConvert, outputStream));
    }
}