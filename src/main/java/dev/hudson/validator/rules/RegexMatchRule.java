package dev.hudson.validator.rules;

public class RegexMatchRule implements Rule {
    private final String regex;

    public RegexMatchRule(String regex){
        if (regex == null || regex.isEmpty()){
            throw new IllegalArgumentException("Regex pattern cannot be null or empty");
        }
        this.regex = regex;
    }

    public boolean check(String value){
        if (value == null) return false;
        return value.matches(regex);
    }
}

