package dev.hudson.validator.rules;

public class IsIntegerRule {
    public boolean check(String value) {
        if (value == null) return false;
        try {
            Integer.parseInt(value.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
