package pairmatching.validator;

import java.util.List;

public class FunctionValidator {

    public final static String BLANK_ERROR_MESSAGE = "[ERROR] 공백만 입력 되었습니다.";
    public final static String INVALID_ERROR_MESSAGE = "[ERROR] 유효하지 않은 입력값입니다.";

    public static void validate(String input) {
        InputValidator.requireNotBlank(input, BLANK_ERROR_MESSAGE);
        isRuleRight(input);
    }

    private static void isRuleRight(String input) {
        List<String> rule = List.of("1", "2", "3", "Q");
        if (!rule.contains(input)) {
            throw new IllegalArgumentException(INVALID_ERROR_MESSAGE);
        }
    }
}
