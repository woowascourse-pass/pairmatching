package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.util.Parser;

public class InputView {

    public final static String INPUT_FUNCTION_MESSAGE = "기능을 선택하세요.\n1. 페어 매칭\n2. 페어 조회\n3. 페어 초기화\nQ. 종료";

    public String inputFunction() {
        System.out.println(INPUT_FUNCTION_MESSAGE);
        return Parser.removeAllSpaces(Console.readLine());
    }
    
}
