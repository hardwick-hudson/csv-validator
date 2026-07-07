package dev.hudson.validator.rules;

public class InRangeRule implements Rule {
    private final int min;
    private final int max;

    public InRangeRule(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Lower boundary cannot be greater than upper boundary");
        }
        this.min = min;
        this.max = max;
    }
    public boolean check(String value){
        if (value == null) return false;
        try {
            int parsed = Integer.parseInt(value.trim());
            return parsed >= min && parsed <= max;
        }
        catch(NumberFormatException e){
            return false;
            }
    }
}
