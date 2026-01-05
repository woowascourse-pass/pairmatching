package pairmatching.dto;

import java.util.List;
import java.util.Set;

public record PairMatchResult(boolean alreadyExist, List<Set<String>> pairList) {

}
