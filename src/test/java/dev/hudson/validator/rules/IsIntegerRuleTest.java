package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IsIntegerRuleTest {
    @Test
    void check_returnsTrue_whenValueIsPositive(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("40");
        assertTrue(result);
    }
    @Test
    void check_returnsTrue_whenValueIsNegative(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("-7");
        assertTrue(result);
    }
    @Test
    void check_returnsFalse_whenValueIsAlphabetical(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("abc");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsEmptyString(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsNull(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check(null);
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsFloat(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("1.5");
        assertFalse(result);
    }
    @Test
    void check_returnsTrue_whenValuePaddedWithWhitespace(){
        IsIntegerRule rule = new IsIntegerRule();
        boolean result = rule.check("   40   ");
        assertTrue(result);
    }
}


