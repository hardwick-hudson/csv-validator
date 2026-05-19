package dev.hudson.validator.rules;

public class NotNullRule {
    public boolean check(String value) {
        if (value == null) return false;
        return !value.trim().isEmpty();
        }
    }
