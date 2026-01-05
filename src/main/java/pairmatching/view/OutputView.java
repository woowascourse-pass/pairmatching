package pairmatching.view;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.dto.PairMatchResult;

public class OutputView {

    private static String PAIR_RESULT = "페어 매칭 결과입니다.";

    private static final String PAIR_INFO =
            "#############################################\n"
                    + "과정: 백엔드 | 프론트엔드\n"
                    + "미션:\n"
                    + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                    + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                    + "  - 레벨3: \n"
                    + "  - 레벨4: 성능개선 | 배포\n"
                    + "  - 레벨5: \n"
                    + "############################################\n";

    public void printPair(PairMatchResult result) {

        System.out.println();
        System.out.println(PAIR_RESULT);
        List<Set<String>> sets = result.pairList();

        for (Set<String> set : sets) {
            String collect = set.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(" : "));
            System.out.println(collect);
        }
        System.out.println();
    }

    public void printPairInfo() {
        System.out.println();
        System.out.print(PAIR_INFO);
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println();
    }
}
