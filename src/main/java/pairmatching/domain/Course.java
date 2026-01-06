package pairmatching.domain;

import java.util.Arrays;
import pairmatching.message.ErrorMessage;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    // 추가 기능 구현

    public static Course findCourse(String courseName) {
        return Arrays.stream(values()).filter(course -> course.name.equals(courseName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_PAIR_INFO.getMessage()));
    }
}
