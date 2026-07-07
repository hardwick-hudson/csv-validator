package dev.hudson.validator;

import dev.hudson.validator.rules.InRangeRule;
import dev.hudson.validator.rules.IsIntegerRule;
import dev.hudson.validator.rules.NotNullRule;
import dev.hudson.validator.rules.RegexMatchRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void parseRuleSpec_buildsNotNullRule() {
        Main.ParsedRule parsed = Main.parseRuleSpec("0:notnull");
        assertEquals(0, parsed.column());
        assertInstanceOf(NotNullRule.class, parsed.rule());
    }
    @Test
    void parseRuleSpec_buildsIsIntegerRule() {
        Main.ParsedRule parsed = Main.parseRuleSpec("1:int");
        assertEquals(1, parsed.column());
        assertInstanceOf(IsIntegerRule.class, parsed.rule());
    }
    @Test
    void parseRuleSpec_buildsInRangeRule() {
        Main.ParsedRule parsed = Main.parseRuleSpec("1:range:0:120");
        assertInstanceOf(InRangeRule.class, parsed.rule());
    }
    @Test
    void parseRuleSpec_buildsRegexRule_withColonsInPattern() {
        Main.ParsedRule parsed = Main.parseRuleSpec("2:regex:^[0-9]{2}:[0-9]{2}$");
        assertInstanceOf(RegexMatchRule.class, parsed.rule());
        assertTrue(parsed.rule().check("12:30"));
    }
    @Test
    void parseRuleSpec_throws_whenUnknownRule(){
        assertThrows(IllegalArgumentException.class, () -> Main.parseRuleSpec("0:uppercase"));
    }
    @Test
    void parseRuleSpec_throws_whenColumnNotANumber(){
        assertThrows(IllegalArgumentException.class, () -> Main.parseRuleSpec("a:int"));
    }
    @Test
    void parseRuleSpec_throws_whenRangeMissingBounds(){
        assertThrows(IllegalArgumentException.class, () -> Main.parseRuleSpec("1:range:5"));
    }
}
