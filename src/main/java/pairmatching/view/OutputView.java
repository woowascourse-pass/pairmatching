package pairmatching.view;

import java.util.List;
import pairmatching.domain.Pair;

public class OutputView {

    public final static String PAIR_MATCHING_RESULT_MESSAGE = "\n페어 매칭 결과입니다.";

    public final static String COURSE_MESSAGE = "\n#############################################\n"
        + "과정: 백엔드 | 프론트엔드\n"
        + "미션:\n"
        + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
        + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
        + "  - 레벨3: \n"
        + "  - 레벨4: 성능개선 | 배포\n"
        + "  - 레벨5: \n"
        + "############################################";

    public final static String INIT_MESSAGE = "\n초기화 되었습니다.";

    public void printPairMatchingResult(List<Pair> pairs) {
        System.out.println(PAIR_MATCHING_RESULT_MESSAGE);
        for (Pair pair : pairs) {
            System.out.println(pair.format());
        }
    }

    public void printCourseMessage() {
        System.out.print(COURSE_MESSAGE);
    }

    public void printInitialMessage() {
        System.out.println(INIT_MESSAGE);
    }
}
