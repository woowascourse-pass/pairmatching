package pairmatching.util;

import java.util.Arrays;
import java.util.List;

public final class Parser {
    private static final String DEFAULT_DELIMITER = "[,]";

    private Parser() {
    }

    public static List<String> parseByDelimiter(final String input) {
        return parseByDelimiter(input, DEFAULT_DELIMITER);
    }

    public static List<String> parseByDelimiter(final String input, final String delimiter) {
        if (input == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다. ");
        }
        return Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .toList();
    }

    public static Integer parseInt(final String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] " + input + " 는 숫자가 아닙니다.");
        }
    }
}