package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexMatchRuleTest {
    private final RegexMatchRule rule = new RegexMatchRule("\\d+");

    @Test
    void check_returnsTrue_whenMatchingValue(){
        assertTrue(rule.check("1234"));
    }
    @Test
    void check_returnsFalse_whenNoMatchingValue(){
        assertFalse(rule.check("abc"));
    }
    @Test
    void check_returnsFalse_whenNull(){
        assertFalse(rule.check(null));
    }
    @Test
    void check_returnsFalse_whenEmpty(){
        assertFalse(rule.check(""));
    }

    @Test
    void constructor_throws_IllegalArgumentException_whenPatternIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new RegexMatchRule(null));
    }
    @Test
    void constructor_throws_IllegalArgumentException_whenPatternIsEmpty(){
        assertThrows(IllegalArgumentException.class, () -> new RegexMatchRule(""));
    }
}

