//package dev.hudson.validator;
//
//import dev.hudson.validator.rules.IsIntegerRule;
//import dev.hudson.validator.rules.Rule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ValidatorTest {
//    private Report report;
//
//    private static String resourcePath(String filename) {
//        var resource = ValidatorTest.class.getClassLoader().getResource(filename);
//        if (resource == null) throw new IllegalStateException(filename + " not found in test/resources");
//        return resource.getPath();
//    }
//    private final String path = resourcePath("test.csv");
//
//    @BeforeEach
//    void setUp() throws IOException{
//        Map<Integer, List<Rule>> rules = Map.of(1, List.of(new IsIntegerRule()));
//        Validator validator = new Validator(rules);
//        report = validator.validate(path);
//    }
//    @Test
//    void validate_returnsCorrectTotalRows(){
//        assertEquals(10, report.totalRows());
//    }
//    @Test
//    void validate_returnsZeroFailCount_whenAllDataValid(){
//        assertEquals(0, report.failCount());
//    }
//    @Test
//    void validate_returnsEmptyFailuresList_whenAllDataValid(){
//        assertEquals(List.of(), report.failures());
//    }
//}
//
