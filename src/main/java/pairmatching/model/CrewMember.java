package pairmatching.model;

public class CrewMember {
    private final Course course;
    private final String name;

    public CrewMember(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
