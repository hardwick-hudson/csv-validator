package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotNullRuleTest {
    private final NotNullRule rule = new NotNullRule();

   @Test
    void check_returnsTrue_whenValueIsPopulated(){
       assertTrue(rule.check("hello"));
    }
    @Test
    void check_returnsTrue_whenValueHasContentWithSpaces(){
        assertTrue(rule.check("a b c"));
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
    void check_returnsFalse_whenValueIsSingleSpace(){
        assertFalse(rule.check(" "));
    }
    @Test
    void check_returnsFalse_whenValueIsMultipleSpaces(){
        assertFalse(rule.check("   "));
    }
    @Test
    void check_returnsFalse_whenValueIsTab(){
        assertFalse(rule.check("\t"));
    }
}

