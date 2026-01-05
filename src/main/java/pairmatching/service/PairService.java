package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
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
        checkRematch(rematch, pairInfo);

        // 이미 존재하는 경우
        if (isAlreadyExist(pairInfo) && !rematch) {
            return new PairMatchResult(true, null);
        }

        List<Pair> history = getHistoryInSameLevel(pairInfo);
        List<String> crewNames = getCrewNames(pairInfo);
        Pair pair = attemptPairMatching(crewNames, history, pairInfo);
        pairList.add(pair);

        return new PairMatchResult(false, pair.retrievePair());
    }

    private Pair attemptPairMatching(List<String> crewNames, List<Pair> history, PairInfo pairInfo) {
        for (int i = 0; i < 3; i++) {
            List<String> shuffled = new ArrayList<>(Randoms.shuffle(crewNames));
            Pair pair = new Pair(pairInfo);

            // 매칭에 성공하면 즉시 반환
            if (matchCurrentCrew(pair, shuffled, history)) {
                return pair;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.PAIR_MATCH_FAILED.getMessage());
    }

    // 한 번의 셔플된 목록으로 끝까지 매칭을 시도. 실패하면 false 반환.
    private boolean matchCurrentCrew(Pair pair, List<String> shuffled, List<Pair> history) {
        while (!shuffled.isEmpty()) {
            if (shuffled.size() == 1) {
                return tryMatchLastOne(pair, shuffled, history);
            }
            if (!tryMatchTwo(pair, shuffled, history)) {
                return false;
            }
        }
        return true;
    }

    // 마지막 1명 매칭 시도 (3명 페어로 해야되는경우)
    private boolean tryMatchLastOne(Pair pair, List<String> shuffled, List<Pair> history) {
        String lastCrew = shuffled.get(0);
        List<String> lastGroup = new ArrayList<>(pair.getLastGroup());
        lastGroup.add(lastCrew);

        // 만약 이미 페어 맺은 적 있으면 false;
        if (checkAlreadyPair(history, lastGroup)) {
            return false;
        }

        pair.addLast(lastCrew);
        shuffled.clear();
        return true;
    }

    // 2명 매칭 시도
    private boolean tryMatchTwo(Pair pair, List<String> shuffled, List<Pair> history) {
        String crew1 = shuffled.get(0);
        String crew2 = shuffled.get(1);

        if (checkAlreadyPair(history, List.of(crew1, crew2))) {
            return false;
        }

        pair.addPair(List.of(crew1, crew2));
        shuffled.subList(0, 2).clear(); // 앞에서 2명 제거
        return true;
    }

    private List<String> getCrewNames(PairInfo pairInfo) {
        List<Crew> crews = findCrewByCourse(pairInfo);
        return crews.stream().map(Crew::getName).toList();
    }

    private List<Pair> getHistoryInSameLevel(PairInfo pairInfo) {
        return pairList.stream().filter(eachPair -> eachPair.isAlreadyMatchInSameLevel(pairInfo))
                .toList();
    }

    private void checkRematch(boolean rematch, PairInfo pairInfo) {
        if (rematch) {
            Pair pair = pairList.stream().filter(eachPair -> eachPair.isExactlySame(pairInfo)).findFirst().get();
            pairList.remove(pair);
        }
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
