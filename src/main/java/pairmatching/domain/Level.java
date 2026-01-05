package pairmatching.domain;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public static Level of(String name) {
        if (name.equals("레벨1")) {
            return Level.LEVEL1;
        }
        if (name.equals("레벨2")) {
            return Level.LEVEL2;
        }
        if (name.equals("레벨3")) {
            return Level.LEVEL3;
        }
        if (name.equals("레벨4")) {
            return Level.LEVEL4;
        }
        if (name.equals("레벨5")) {
            return Level.LEVEL5;
        }
        return null;
    }
}