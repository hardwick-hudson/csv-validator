package dev.hudson.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    private final CsvReader reader = new CsvReader();
    private static String resourcePath(String filename) {
        var resource = CsvReaderTest.class.getClassLoader().getResource(filename);
        if (resource == null) throw new IllegalStateException(filename + " not found in test/resources");
        return resource.getPath();
    }
    private final String emptyPath = resourcePath("empty.csv");
    private final String path = resourcePath("test.csv");
    private List<List<String>> result;

    @BeforeEach
    void setUp() throws IOException{
        result = reader.read(path);
    }

    @Test
    void check_returnsCorrectRowCount_forValidCsv(){
        assertEquals(11, result.size());
    }
    @Test
    void check_returnsHeader_forValidCsv(){
        assertEquals(List.of("name", "age"), result.get(0));

    }
    @Test
    void check_returnsCorrectPair_forValidCsv(){
        assertEquals(List.of("Bill", "35"), result.get(1));
    }
    @Test
    void check_returnsCorrectCell_forValidCsv(){
        assertEquals("Bill", result.get(1).get(0));
    }
    @Test
    void read_throwsIOException_whenFileNotFound(){
        assertThrows(IOException.class, () -> reader.read("fake.csv"));
    }

    @Test
    void read_returnsEmptyList_whenBlankFile() throws IOException{
        assertEquals(List.of(), reader.read(emptyPath));
    }
}
