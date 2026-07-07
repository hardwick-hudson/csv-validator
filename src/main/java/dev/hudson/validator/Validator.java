package dev.hudson.validator;

import dev.hudson.validator.rules.Rule;

import java.io.IOException;
import java.util.*;

public class Validator {
    private final Map<Integer, List<Rule>> rules;

    public Validator(Map<Integer, List<Rule>> rules) {
        this.rules = rules;
    }

    public Report validate(String filePath) throws IOException {
        CsvReader reader = new CsvReader();
        List<List<String>> rows = reader.read(filePath);

        List<ValidationFailure> failures = new ArrayList<>();
        Set<Integer> failedRows = new HashSet<>();

        // row 0 treated as header; failure rows are data-row indicies starting at 1
        for (int rowIndex = 1; rowIndex < rows.size(); rowIndex++) {
            List<String> row = rows.get(rowIndex);
            for (Map.Entry<Integer, List<Rule>> entry : rules.entrySet()) {
                int column = entry.getKey();
                String value = null;
                if (column < row.size()) {
                    value = row.get(column);
                }
                for (Rule rule : entry.getValue()) {
                    if (!rule.check(value)) {
                        failures.add(new ValidationFailure(rowIndex, column, value, rule.getClass().getSimpleName()));
                        failedRows.add(rowIndex);
                    }
                }
            }
        }
        int totalRows = rows.size() - 1;
        if (totalRows < 0) {
            totalRows = 0;
        }
        int failCount = failedRows.size();
        return new Report(totalRows, totalRows - failCount, failCount, failures);
    }
}
