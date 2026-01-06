package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.model.Course;
import pairmatching.model.Info;
import pairmatching.model.Level;
import pairmatching.model.Mission;
import pairmatching.util.Parser;

import java.util.List;

public class InputView {
    public static String ERROR_PREFIX = "[ERROR] ";

    public String readCommand() {
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");

        String command = Console.readLine().trim();
        validateCommand(command);

        return command;
    }

    public Info readInfo() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");

        String raw = Console.readLine().trim();
        List<String> tokens = Parser.parseByDelimiter(raw);
        validateInfo(tokens);

        Course course = Course.from(tokens.get(0));
        Level level = Level.from(tokens.get(1));
        String missionName = tokens.get(2);
        Mission mission = Mission.find(level, missionName);

        return new Info(course, mission);
    }

    public String readWhether() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");

        String whether = Console.readLine().trim();
        validateWhether(whether);

        return whether;
    }

    private void validateCommand(String command) {

    }

    private void validateInfo(List<String> tokens) {

    }

    private void validateWhether(String whether) {

    }
}
