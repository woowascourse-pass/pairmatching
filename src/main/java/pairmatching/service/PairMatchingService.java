package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Pair;
import pairmatching.domain.PairManager;
import pairmatching.dto.PairMatchingRequest;
import pairmatching.util.ReadText;

public class PairMatchingService {

    private PairManager pairManager = new PairManager();

    public List<String> readCrew(Course course) {
        List<String> crews = new ArrayList<>();
        if (Course.BACKEND == course) {
            crews = readBackend();
        }
        if (Course.FRONTEND == course) {
            crews = readFrontend();
        }
        return crews;
    }

    private List<String> readBackend() {
        List<String> crews = new ArrayList<>();
        ReadText readText = new ReadText("backend-crew");
        String line = readText.readLine();
        while (line != null) {
            crews.add(line);
            line = readText.readLine();
        }
        return crews;
    }

    private List<String> readFrontend() {
        List<String> crews = new ArrayList<>();
        ReadText readText = new ReadText("frontend-crew");
        String line = readText.readLine();
        while (line != null) {
            crews.add(line);
            line = readText.readLine();
        }
        return crews;
    }

    public List<Pair> matchPair(PairMatchingRequest request) {
        List<String> crews = readCrew(request.getCourse());
        for (int i = 0; i < 3; i++) {
            crews = Randoms.shuffle(crews);
            List<Pair> pairs = convertPairs(crews);
            if (isPossible(request.getCourse(), request.getLevel(), request.getMission(), pairs)) {
                pairManager.setPairs(request.getCourse(), request.getLevel(), request.getMission(),
                    pairs);
                return pairs;
            }
        }
        throw new IllegalArgumentException("[ERROR] 매칭이 불가능 합니다.");
    }

    private boolean isPossible(Course course, Level level, String thisMission,
        List<Pair> shuffledCrews) {
        Map<String, List<Pair>> levelPairs = pairManager.findPairsByCourseAndLevel(course, level);
        for (String mission : levelPairs.keySet()) {
            if (mission.equals(thisMission)) {
                continue;
            }
            List<Pair> pairs = levelPairs.get(mission);
            for (int i = 0; i < pairs.size(); i++) {
                Pair batchedPair = pairs.get(i);
                Pair shuffledPair = shuffledCrews.get(i);
                if (batchedPair.contains(shuffledPair)) {
                    return false;
                }
            }
        }
        return true;
    }


    private List<Pair> convertPairs(List<String> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size(); i += 2) {
            if (i == crews.size() - 3) {
                pairs.add(new Pair(crews.get(i), crews.get(i + 1), crews.get(i + 2)));
                break;
            }
            pairs.add(new Pair(crews.get(i), crews.get(i + 1)));
        }
        return pairs;
    }

    public boolean isNoMatching(PairMatchingRequest request) {
        List<Pair> pairs = pairManager.findPairs(request.getCourse(), request.getLevel(),
            request.getMission());
        return pairs.isEmpty();
    }

    public List<Pair> findPairs(PairMatchingRequest request) {
        return pairManager.findPairs(request.getCourse(), request.getLevel(), request.getMission());
    }

    public void initPairManager() {
        pairManager = new PairManager();
    }
}




















