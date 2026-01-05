package pairmatching.validator;

import java.util.List;
import java.util.Map;
import pairmatching.message.ErrorMessage;
import pairmatching.util.Parser;

public class PairMatchingValidator {


    public static void validate(String input) {
        InputValidator.requireNotBlank(input, ErrorMessage.BLANK_ERROR_MESSAGE.getMessage());
        List<String> parsedInput = Parser.parseInput(input, ",");
        if (parsedInput.size() != 3) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ERROR_MESSAGE.getMessage());
        }
        validateCourse(parsedInput.get(0));
        validateLevel(parsedInput.get(1));
        validateMission(parsedInput.get(1), parsedInput.get(2));
    }

    private static void validateCourse(String input) {
        List<String> courses = List.of("백엔드", "프론트엔드");
        if (!courses.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ERROR_MESSAGE.getMessage());
        }
    }

    private static void validateLevel(String input) {
        List<String> courses = List.of("레벨1", "레벨2", "레벨3", "레벨4", "레벨5");
        if (!courses.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ERROR_MESSAGE.getMessage());
        }
    }

    private static void validateMission(String level, String mission) {
        Map<String, List<String>> missions = Map.ofEntries(
            Map.entry("레벨1", List.of("자동차경주", "로또", "숫자아구게임")),
            Map.entry("레벨2", List.of("장바구니", "결제", "지하철노선도")),
            Map.entry("레벨3", List.of()),
            Map.entry("레벨4", List.of("성능개선", "배포")),
            Map.entry("레벨5", List.of())
        );
        if (!missions.getOrDefault(level, List.of()).contains(mission)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ERROR_MESSAGE.getMessage());
        }
    }
}
