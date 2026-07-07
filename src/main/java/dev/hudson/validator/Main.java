package dev.hudson.validator;

import dev.hudson.validator.rules.InRangeRule;
import dev.hudson.validator.rules.IsIntegerRule;
import dev.hudson.validator.rules.NotNullRule;
import dev.hudson.validator.rules.RegexMatchRule;
import dev.hudson.validator.rules.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    record ParsedRule(int column, Rule rule) {}

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: csv-validator <file.csv> <colIndex>:<rule>[:<param>...] ...");
            System.err.println("Rules: notnull | int | range:<min>:<max> | regex:<pattern>");
            System.exit(2);
        }

        String filePath = args[0];
        Map<Integer, List<Rule>> rules = new HashMap<>();

        try {
            for (int i = 1; i < args.length; i++) {
                ParsedRule parsed = parseRuleSpec(args[i]);
                if (!rules.containsKey(parsed.column())){
                    rules.put(parsed.column(), new ArrayList<>());
                }
                rules.get(parsed.column()).add(parsed.rule());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        }

        try {
            Validator validator = new Validator(rules);
            Report report = validator.validate(filePath);
            printReport(report);
            if (report.failCount() > 0) {
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not read file: " + filePath);
            System.exit(2);
        }
    }

    static ParsedRule parseRuleSpec(String spec) {
        String [] parts = spec.split(":", 3);
        if (parts.length < 2){
            throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": expected <colIndex>:<rule>[:<param>...]");
        }

        int column;
        try {
            column = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": column index must be a number");
        }
        if (column < 0){
            throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": column index must not be negative");
        }

        String ruleName = parts[1];
        switch(ruleName){
            case "notnull":
                return new ParsedRule(column, new NotNullRule());
            case "int":
                return new ParsedRule(column, new IsIntegerRule());
            case "range": {
                if (parts.length < 3){
                    throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": range needs <min>:<max>");
                }
                String[] bounds = parts[2].split(":", 2);
                if (bounds.length < 2) {
                    throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": range needs <min>:<max>");
                }
                try {
                    int min = Integer.parseInt(bounds[0]);
                    int max = Integer.parseInt(bounds[1]);
                    return new ParsedRule(column, new InRangeRule(min, max));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": range bounds must be numbers");
                }
            }
            case "regex":
                if (parts.length < 3 || parts[2].isEmpty()){
                    throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": regex needs a pattern");
                }
                return new ParsedRule(column, new RegexMatchRule(parts[2]));
            default:
                throw new IllegalArgumentException("Bad rule spec \"" + spec + "\": unknown rule \"" + ruleName + "\"");
        }
    }
    static void printReport(Report report) {
        for (ValidationFailure failure : report.failures()){
            System.out.println("row " + failure.row() + ", col " + failure.column() + ": \"" + failure.value() + "\" failed " + failure.rule());
        }
        System.out.println(report.passCount() + "/" + report.totalRows() + " rows passed");
    }
}
