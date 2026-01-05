package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.domain.PairInfo;
import pairmatching.dto.PairMatchResult;
import pairmatching.message.ErrorMessage;
import pairmatching.util.InputFileReader;

public class PairService {

    private final List<Crew> front;
    private final List<Crew> back;
    private final List<Pair> pairList = new ArrayList<>();

    public PairService(InputFileReader inputFileReader) {
        front = inputFileReader.readFront();
        back = inputFileReader.readBack();
    }

    // false - 이미 존재하거나, 페어 매칭 실패 시 false;
    public PairMatchResult pairMatching(boolean rematch, List<String> rawPairInfo) {

        PairInfo pairInfo = makePairInfo(rawPairInfo);

        // 재매치 요청 경우 기존 페어 삭제
        if (rematch) {
            Pair pair = pairList.stream().filter(eachPair -> eachPair.isExactlySame(pairInfo)).findFirst().get();
            pairList.remove(pair);
        }

        // 이미 존재하는 경우
        if (isAlreadyExist(pairInfo) && !rematch) {
            return new PairMatchResult(true, null);
        }

        // 같은 레벨의 다른 미션에서 매칭된 적이 있는 경우 매칭 시켜선 안됨
        // 과정, 레벨은 같고 미션은 다른 페어가 있는지 확인
        List<Pair> alreadyPair = pairList.stream().filter(eachPair -> eachPair.isAlreadyMatchInSameLevel(pairInfo))
                .toList();

        List<Crew> crews = findCrewByCourse(pairInfo);
        List<String> crewNames = crews.stream().map(Crew::getName).toList();

        Pair pair = new Pair(pairInfo);
        List<String> shuffled = new ArrayList<>(Randoms.shuffle(crewNames));
        int count = 1;
        // 먼저 shuffled에서 앞에서 2명 꺼내오기
        //TODO : 매칭할 경우의 수 없는 경우...?
        while (!shuffled.isEmpty()) {

            if (count == 4) {
                throw new IllegalArgumentException(ErrorMessage.PAIR_MATCH_FAILED.getMessage());
            }

            if (shuffled.size() == 1) {
                // 여기 들어온 건 1명 남았다는 이야기
                Set<String> lastGroup = pair.getLastGroup();
                lastGroup = new HashSet<>(lastGroup);
                lastGroup.add(shuffled.get(0));

                boolean already = checkAlreadyPair(alreadyPair, List.copyOf(lastGroup));
                if (already) {
                    // 그전까지의 페어 초기화
                    pair.clearPair();
                    shuffled = new ArrayList<>(Randoms.shuffle(crewNames));
                    count++;
                    continue;
                }

                // 페어 맺은 적 없다면 정상적으로 페어 추가
                pair.addLast(shuffled.get(0));
                // 그후 페어 추가한 사람은 지워야함.
                shuffled.remove(0);
                continue;
            }

            String first = shuffled.get(0);
            String second = shuffled.get(1);

            // 이미 페어 맺은 적 있는지 유무
            // list에서 이미 매칭된 적 있는 조합으론 매칭되어선 안됨
            boolean already = checkAlreadyPair(alreadyPair, List.of(first, second));
            if (already) {
                // 그전까지의 페어 초기화
                pair.clearPair();
                shuffled = new ArrayList<>(Randoms.shuffle(crewNames));
                count++;
                continue;
            }

            // 페어 맺은 적 없다면 정상적으로 페어 추가
            pair.addPair(List.of(first, second));
            // 그후 페어 추가한 사람은 지워야함.
            shuffled.remove(0);
            shuffled.remove(0);
        }

        pairList.add(pair);
        return new PairMatchResult(false, pair.retrievePair());
    }

    private PairInfo makePairInfo(List<String> pairInfo) {
        Course course = Course.findCourse(pairInfo.get(0));
        Level level = Level.findLevel(pairInfo.get(1));
        Mission mission = Mission.findMission(pairInfo.get(2), level);

        return new PairInfo(course, level, mission);
    }


    private boolean checkAlreadyPair(List<Pair> checkedPair, List<String> crews) {
        return checkedPair.stream().anyMatch(eachPair -> eachPair.checkCrew(crews));
    }

    private List<Crew> findCrewByCourse(PairInfo pairInfo) {
        if (pairInfo.getCourse() == Course.FRONTEND) {
            return front;
        }

        return back;
    }

    private boolean isAlreadyExist(PairInfo pairInfo) {
        return pairList.stream().anyMatch(pair -> pair.isExactlySame(pairInfo));
    }

    public PairMatchResult retrievePair(List<String> rawPairInfo) {

        PairInfo pairInfo = makePairInfo(rawPairInfo);

        // 존재하지 않는 경우
        if (!isAlreadyExist(pairInfo)) {
            return new PairMatchResult(false, null);
        }

        //존재하는 경우
        Pair pair = pairList.stream().filter(eachPair -> eachPair.isExactlySame(pairInfo)).findFirst().get();

        return new PairMatchResult(true, pair.retrievePair());
    }

    public void initializePair() {
        pairList.clear();
    }
}
