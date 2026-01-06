package pairmatching.model;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course from(String name) {
        String trimmed = name.trim();
        for (Course course : Course.values()) {
            if (trimmed.equals(course.getName())) {
                return course;
            }
        }

        throw new IllegalArgumentException("없는 코스 ㅜㅜ");
    }
}