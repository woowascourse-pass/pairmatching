package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.model.Course;
import pairmatching.model.Info;
import pairmatching.model.Level;
import pairmatching.model.Mission;
import pairmatching.util.Parser;

import java.util.List;

public class InputView {
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

        Course course = Course.from(tokens.get(0));
        Level level = Level.from(tokens.get(1));
        String missionName = tokens.get(2);
        Mission mission = Mission.find(level, missionName);
        validateMission(mission);

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
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("기능이 비었어요.");
        }

        if (!command.equals("1") && !command.equals("2")
                && !command.equals("3") && !command.equals("Q")) {
            throw new IllegalArgumentException(command + "는 없는 기능");
        }
    }

    private void validateWhether(String whether) {
        if (!whether.equals("예") && !whether.equals("아니오")) {
            throw new IllegalArgumentException("대답이 잘못됐어요");
        }
    }

    private void validateMission(Mission mission) {
        if (mission == Mission.None) {
            throw new IllegalArgumentException(mission + "은 없는 미션");
        }
    }
}
