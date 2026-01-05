package pairmatching.domain;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course of(String name) {
        if (name.equals("백엔드")) {
            return Course.BACKEND;
        }
        if (name.equals("프론트엔드")) {
            return Course.FRONTEND;
        }
        return null;
    }
}