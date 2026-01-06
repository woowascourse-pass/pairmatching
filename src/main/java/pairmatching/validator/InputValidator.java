package pairmatching.validator;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.message.ErrorMessage;

public class InputValidator {

    public static String validateSelectedFunction(String input) {
        if (input.equals("Q")) {
            return input;
        }

        if (!(input.equals("1")) && !(input.equals("2")) && !(input.equals("3"))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }

        return input;
    }

    public static void validatePairInfo(List<String> pairInfo) {
        String course = pairInfo.get(0);
        Course.findCourse(course);

        String level = pairInfo.get(1);
        Level foundLevel = Level.findLevel(level);

        String mission = pairInfo.get(2);
        Mission.findMission(mission, foundLevel);
    }
}
