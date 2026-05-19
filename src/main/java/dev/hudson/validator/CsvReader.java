package dev.hudson.validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public List<List<String>> read(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        List<List<String>> rows = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            rows.add(Arrays.stream(parts).map(String::trim).collect(Collectors.toList()));
        }
        return rows;
    }
}


