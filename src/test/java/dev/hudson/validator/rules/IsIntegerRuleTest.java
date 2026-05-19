package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsIntegerRuleTest {
    private final IsIntegerRule rule = new IsIntegerRule();

    @Test
    void check_returnsTrue_whenValueIsPositive(){
        boolean result = rule.check("40");
        assertTrue(result);
    }
    @Test
    void check_returnsTrue_whenValueIsNegative(){
        boolean result = rule.check("-7");
        assertTrue(result);
    }
    @Test
    void check_returnsFalse_whenValueIsAlphabetical(){
        boolean result = rule.check("abc");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsEmptyString(){
        boolean result = rule.check("");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsNull(){
        boolean result = rule.check(null);
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsFloat(){
        boolean result = rule.check("1.5");
        assertFalse(result);
    }
    @Test
    void check_returnsTrue_whenValuePaddedWithWhitespace(){
        boolean result = rule.check("   40   ");
        assertTrue(result);
    }
}


