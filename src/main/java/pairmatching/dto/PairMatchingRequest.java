package pairmatching.dto;

import pairmatching.domain.Course;
import pairmatching.domain.Level;

public class PairMatchingRequest {

    private Course course;
    private Level level;
    private String mission;

    public PairMatchingRequest(String course, String level, String mission) {
        this.course = Course.of(course);
        this.level = Level.of(level);
        this.mission = mission;
    }
}
