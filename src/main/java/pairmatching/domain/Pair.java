package pairmatching.domain;

public class Pair {

    private String crew1;
    private String crew2;
    private String crew3;

    public String getCrew1() {
        return crew1;
    }

    public String getCrew2() {
        return crew2;
    }

    public String getCrew3() {
        return crew3;
    }

    public Pair(String crew1, String crew2) {
        this.crew1 = crew1;
        this.crew2 = crew2;
    }

    public Pair(String crew1, String crew2, String crew3) {
        this.crew1 = crew1;
        this.crew2 = crew2;
        this.crew3 = crew3;
    }

    public String format() {
        if (crew3 != null) {
            return String.format("%s : %s : %s", crew1, crew2,
                crew3);
        }
        return String.format("%s : %s", crew1, crew2);
    }

    public boolean contains(Pair pair) {
        if (pair.getCrew3() != null) {
            return containsName(pair.getCrew1()) && containsName(pair.getCrew2()) && containsName(
                pair.getCrew3());
        }
        return containsName(pair.getCrew1()) && containsName(pair.getCrew2());
    }

    private boolean containsName(String name) {
        if (crew1.equals(name)) {
            return true;
        }
        if (crew2.equals(name)) {
            return true;
        }
        return crew3 != null && crew3.equals(name);
    }

    public int size() {
        if (this.crew3 != null) {
            return 3;
        }
        return 2;
    }
}
