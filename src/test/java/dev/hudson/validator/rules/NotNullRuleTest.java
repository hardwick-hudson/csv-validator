package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class NotNullRuleTest {
   @Test
    void check_returnsTrue_whenValueIsPopulated() {
       NotNullRule rule = new NotNullRule();
       boolean result = rule.check("hello");
       assertTrue(result);
    }
    @Test
    void check_returnsTrue_whenValueHasContentWithSpaces() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check("a b c");
        assertTrue(result);
    }
    @Test
    void check_returnsFalse_whenValueIsEmptyString() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check("");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsNull() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check(null);
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsSingleSpace() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check(" ");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsMultipleSpaces() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check("   ");
        assertFalse(result);
    }
    @Test
    void check_returnsFalse_whenValueIsTab() {
        NotNullRule rule = new NotNullRule();
        boolean result = rule.check("\t");
        assertFalse(result);
    }
    }
