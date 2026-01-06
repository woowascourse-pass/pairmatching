package pairmatching.model;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5"),
    None("없음");

    private String name;

    Level(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Level from(String levelName) {
        String trimmed = levelName.trim();
        for (Level level : Level.values()) {
            if (trimmed.equals(level.getName())) {
                return level;
            }
        }

        throw new IllegalArgumentException("없는 레벨 ㅜㅜ");
    }
}