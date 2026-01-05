package pairmatching.validator;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {

    public static void requireNotBlank(String input, String message) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireDigits(String input, String message) {
        for (char ch : input.toCharArray()) {
            if (!Character.isDigit(ch)) {
                throw new IllegalArgumentException(message);
            }
        }
    }

    public static int parseIntOrThrow(String input, String message) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requireNotDuplicate(List<?> input, String message) {
        Set<?> set = new HashSet<>(input);
        if (set.size() != input.size()) {
            throw new IllegalArgumentException(message);
        }
    }
}
