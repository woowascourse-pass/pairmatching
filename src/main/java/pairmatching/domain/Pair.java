package pairmatching.domain;

public class Pair {

    private Crew crew1;
    private Crew crew2;
    private Crew crew3;

    public Pair(Crew crew1, Crew crew2) {
        this.crew1 = crew1;
        this.crew2 = crew2;
    }

    public Pair(Crew crew1, Crew crew2, Crew crew3) {
        this.crew1 = crew1;
        this.crew2 = crew2;
        this.crew3 = crew3;
    }

    public String format() {
        if (crew3 != null) {
            return String.format("%s : %s : %s", crew1.getName(), crew2.getCourse(),
                crew3.getName());
        }
        return String.format("%s : %s", crew1.getName(), crew2.getCourse());
    }
}
