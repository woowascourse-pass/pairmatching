package pairmatching.domain;

import java.util.Arrays;
import pairmatching.message.ErrorMessage;

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

    public static Level findLevel(String levelName) {
        return Arrays.stream(values()).filter(level -> level.name.equals(levelName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_PAIR_INFO.getMessage()));
    }

    // 추가 기능 구현
}
