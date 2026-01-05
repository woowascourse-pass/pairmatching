package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pair {

    private final List<Set<String>> pairList = new ArrayList<>();
    private final PairInfo pairinfo;

    public Pair(PairInfo pairInfo) {
        this.pairinfo = pairInfo;
    }

    public boolean isExactlySame(PairInfo pairInfo) {
        return this.pairinfo.equals(pairInfo);
    }

    public boolean isAlreadyMatchInSameLevel(PairInfo pairInfo) {
        return this.pairinfo.isAlreadyMatchInSameLevel(pairInfo);
    }

    // List<Crew>가 여기에 존재하는지 파악하는 메소드 필요
    public boolean checkCrew(List<String> names) {
        return pairList.stream()
                .anyMatch(pairList -> pairList.containsAll(names) && pairList.size() == names.size());
    }

    public void addPair(List<String> crews) {
        pairList.add(new HashSet<>(crews));
    }

    public void clearPair() {
        pairList.clear();
    }

    public Set<String> getLastGroup() {
        return pairList.get(pairList.size() - 1);
    }

    // 1명 남았을 경우
    public void addLast(String last) {
        Set<String> lastGroup = pairList.get(pairList.size() - 1);
        pairList.remove(lastGroup);
        lastGroup = new HashSet<>(lastGroup);
        lastGroup.add(last);
        pairList.add(lastGroup);
    }

    public List<Set<String>> retrievePair() {
        return List.copyOf(pairList);
    }
}
