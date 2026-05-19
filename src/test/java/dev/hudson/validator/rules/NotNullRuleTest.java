package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotNullRuleTest {
    private final NotNullRule rule = new NotNullRule();

   @Test
    void check_returnsTrue_whenValueIsPopulated(){
       boolean result = rule.check("hello");
       assertTrue(result);
    }
    @Test
    void check_returnsTrue_whenValueHasContentWithSpaces(){
        boolean result = rule.check("a b c");
        assertTrue(result);
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
    void check_returnsFalse_whenValueIsSingleSpace(){
        boolean result = rule.check(" ");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsMultipleSpaces(){
        boolean result = rule.check("   ");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsTab(){
        boolean result = rule.check("\t");
        assertFalse(result);
    }
    }

