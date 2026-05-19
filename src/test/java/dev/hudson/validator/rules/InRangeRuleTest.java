package dev.hudson.validator.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InRangeRuleTest {
    private final InRangeRule rule = new InRangeRule(1, 5);

    @Test
    void check_returnsTrue_whenInRange(){
        assertTrue(rule.check("4"));
    }
    @Test
    void check_returnsTrue_whenLowerBoundEqual(){
        assertTrue(rule.check("1"));
    }
    @Test
    void check_returnsTrue_whenUpperBoundEquals(){
        assertTrue(rule.check("5"));
    }
    @Test
    void check_returnsFalse_whenBelowLowerBound(){
        assertFalse(rule.check("0"));
    }
    @Test
    void check_returnsFalse_whenOverUpperBound(){
        assertFalse(rule.check("6"));
    }
    @Test
    void check_returnsFalse_whenNonNumeric(){
        assertFalse(rule.check("a"));
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
    void constructor_throwsIllegalArgumentException_whenMixGreaterThanMax(){
        assertThrows(IllegalArgumentException.class, () -> new InRangeRule(5, 1));
    }
}

