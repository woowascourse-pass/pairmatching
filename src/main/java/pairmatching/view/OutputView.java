package pairmatching.view;

import java.util.List;
import pairmatching.domain.Pair;

public class OutputView {

    public void printPairMatchingResult(List<Pair> pairs) {
        for (Pair pair : pairs) {
            System.out.println(pair.format());
        }
    }

    public void printInitialMessage() {
        System.out.println("초기화 되었습니다.");
    }
}
