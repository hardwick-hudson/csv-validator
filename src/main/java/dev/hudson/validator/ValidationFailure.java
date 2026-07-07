package dev.hudson.validator;

public record ValidationFailure(int row, int column, String value, String rule) {}
