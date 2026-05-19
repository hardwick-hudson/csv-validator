package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsIntegerRuleTest {
    private final IsIntegerRule rule = new IsIntegerRule();

    @Test
    void check_returnsTrue_whenValueIsPositive(){
        assertTrue(rule.check("40"));
    }
    @Test
    void check_returnsTrue_whenValueIsNegative(){
        assertTrue(rule.check("-7"));
    }
    @Test
    void check_returnsFalse_whenValueIsAlphabetical(){
        assertFalse(rule.check("abc"));
    }
    @Test
    void check_returnsFalse_whenValueIsEmptyString(){
        assertFalse(rule.check(""));
    }
    @Test
    void check_returnsFalse_whenValueIsNull(){
        assertFalse(rule.check(null));
    }
    @Test
    void check_returnsFalse_whenValueIsFloat(){
        assertFalse(rule.check("1.5"));
    }
    @Test
    void check_returnsTrue_whenValuePaddedWithWhitespace(){
        assertTrue(rule.check("   40   "));
    }
}


