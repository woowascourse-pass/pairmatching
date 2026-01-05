package pairmatching.validator;

import java.util.List;
import pairmatching.message.ErrorMessage;

public class FunctionValidator {

    public static void validate(String input) {
        InputValidator.requireNotBlank(input, ErrorMessage.BLANK_ERROR_MESSAGE.getMessage());
        isRuleRight(input);
    }

    private static void isRuleRight(String input) {
        List<String> rule = List.of("1", "2", "3", "Q");
        if (!rule.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ERROR_MESSAGE.getMessage());
        }
    }
}
