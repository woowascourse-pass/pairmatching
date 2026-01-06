package pairmatching.model;

import java.util.Arrays;

public enum Mission {
    RacingCar(Level.LEVEL1, "자동차경주"),
    Lotto(Level.LEVEL1, "로또"),
    NumberBaseball(Level.LEVEL1, "숫자야구게임"),

    ShoppingBasket(Level.LEVEL2, "장바구니"),
    Payment(Level.LEVEL2, "결제"),
    SubwayMap(Level.LEVEL2, "지하철노선도"),
// none

    Improvement(Level.LEVEL4, "성능개선"),
    Distribution(Level.LEVEL4, "배포"),

    None(null, "없음");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public Level getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static Mission find(Level level,String missionName) {
        return Arrays.stream(values())
                .filter(mission -> mission.level.equals(level)
                        && mission.name.equals(missionName))
                .findFirst()
                .orElse(None);
    }

}
