package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairManager {

    private final Map<Course, Map<Level, Map<String, List<Pair>>>> pairs;

    public PairManager() {
        pairs = new HashMap<>();

        pairs.put(Course.BACKEND, new HashMap<>());
        pairs.get(Course.BACKEND).put(Level.LEVEL1, new HashMap<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL1).put("자동차경주", new ArrayList<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL1).put("로또", new ArrayList<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL1).put("숫자야구게임", new ArrayList<>());
        pairs.get(Course.BACKEND).put(Level.LEVEL2, new HashMap<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL2).put("장바구니", new ArrayList<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL2).put("결제", new ArrayList<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL2).put("지하철노선도", new ArrayList<>());
        pairs.get(Course.BACKEND).put(Level.LEVEL3, new HashMap<>());
        pairs.get(Course.BACKEND).put(Level.LEVEL4, new HashMap<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL4).put("성능개선", new ArrayList<>());
        pairs.get(Course.BACKEND).get(Level.LEVEL4).put("배포", new ArrayList<>());
        pairs.get(Course.BACKEND).put(Level.LEVEL5, new HashMap<>());

        pairs.put(Course.FRONTEND, new HashMap<>());
        pairs.get(Course.FRONTEND).put(Level.LEVEL1, new HashMap<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL1).put("자동차경주", new ArrayList<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL1).put("로또", new ArrayList<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL1).put("숫자야구게임", new ArrayList<>());
        pairs.get(Course.FRONTEND).put(Level.LEVEL2, new HashMap<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL2).put("장바구니", new ArrayList<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL2).put("결제", new ArrayList<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL2).put("지하철노선도", new ArrayList<>());
        pairs.get(Course.FRONTEND).put(Level.LEVEL3, new HashMap<>());
        pairs.get(Course.FRONTEND).put(Level.LEVEL4, new HashMap<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL4).put("성능개선", new ArrayList<>());
        pairs.get(Course.FRONTEND).get(Level.LEVEL4).put("배포", new ArrayList<>());
        pairs.get(Course.FRONTEND).put(Level.LEVEL5, new HashMap<>());
    }

    public List<Pair> findPairs(Course course, Level level, String mission) {
        return pairs.getOrDefault(course, new HashMap<>()).getOrDefault(level, new HashMap<>())
            .getOrDefault(mission, new ArrayList<>());
    }

    public Map<String, List<Pair>> findPairsByCourseAndLevel(Course course, Level level) {
        return pairs.getOrDefault(course, new HashMap<>()).getOrDefault(level, new HashMap<>());
    }

    public Map<Level, Map<String, List<Pair>>> findPairsByCourse(Course course) {
        return pairs.getOrDefault(course, new HashMap<>());
    }

    public void setPairs(Course course, Level level, String mission, List<Pair> pairs) {
        this.pairs.getOrDefault(course, new HashMap<>()).getOrDefault(level, new HashMap<>())
            .put(mission, pairs);
    }

}
