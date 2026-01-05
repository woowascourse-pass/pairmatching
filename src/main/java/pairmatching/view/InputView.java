package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.Parser;

public class InputView {

    public final static String INPUT_FUNCTION_MESSAGE = "\n기능을 선택하세요.\n1. 페어 매칭\n2. 페어 조회\n3. 페어 초기화\nQ. 종료";
    public final static String INPUT_PAIR_MATCHING_MESSAGE =
        "\n과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주";
    public final static String INPUT_PAIR_REMATCHING_MESSAGE = "\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
        + "네 | 아니오";

    public String inputFunction() {
        System.out.println(INPUT_FUNCTION_MESSAGE);
        return Parser.removeAllSpaces(Console.readLine());
    }

    public String inputPairMatching() {
        System.out.println(INPUT_PAIR_MATCHING_MESSAGE);
        return Parser.removeAllSpaces(Console.readLine());
    }

    public String inputPairRematching() {
        System.out.println(INPUT_PAIR_REMATCHING_MESSAGE);
        return Parser.removeAllSpaces(Console.readLine());
    }
}
