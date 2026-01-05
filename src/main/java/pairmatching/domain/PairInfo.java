package pairmatching.domain;

import java.util.Objects;

public class PairInfo {

    private final Course course;
    private final Level level;
    private final Mission mission;

    public PairInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairInfo pairInfo = (PairInfo) o;
        return course == pairInfo.course && level == pairInfo.level && mission == pairInfo.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }

    public boolean isAlreadyMatchInSameLevel(PairInfo pairInfo) {
        return this.course == pairInfo.course && this.level == pairInfo.level && !(this.mission == pairInfo.mission);
    }
}
