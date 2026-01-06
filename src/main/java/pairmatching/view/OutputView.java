package pairmatching.view;

import pairmatching.model.Crew;
import pairmatching.model.CrewMember;

import java.util.List;

public class OutputView {
    public static String ERROR_PREFIX = "[ERROR] ";

    public void printInfo() {
        System.out.println();
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println("\t" + "- 레벨1: 자동차경주 | 로또 | 숫자야구게임");
        System.out.println("\t" + "- 레벨2: 장바구니 | 결제 | 지하철노선도");
        System.out.println("\t" + "- 레벨3: ");
        System.out.println("\t" + "- 레벨4: 성능개선 | 배포");
        System.out.println("\t" + "- 레벨5: ");
        System.out.println("#############################################");
    }

    public void printPairMatchingResult(Crew crew) {
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");
        // 페어 매칭 결과 출력
        List<CrewMember> crewMembers = crew.getCrewMembers();
        // 짝수
        if (crewMembers.size() % 2 == 0) {
            for (int i = 0; i < crewMembers.size(); i += 2) {
                StringBuilder sb = new StringBuilder();
                sb.append(crewMembers.get(i).getName());
                sb.append(" : ");
                sb.append(crewMembers.get(i + 1).getName());
                System.out.println(sb);
            }
            return;
        }

        // 홀수
        for (int i = 0; i < crewMembers.size(); i += 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(crewMembers.get(i).getName());
            sb.append(" : ");
            sb.append(crewMembers.get(i + 1).getName());

            if (i == crewMembers.size() - 3) {
                sb.append(" : ").append(crewMembers.get(i + 2).getName());
                i++;
            }

            System.out.println(sb);
        }
    }

    public void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public void printClear() {
        System.out.println();
        System.out.println("초기화 되었습니다.");
    }
}
