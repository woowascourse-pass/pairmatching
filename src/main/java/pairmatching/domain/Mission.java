package pairmatching.domain;

import static pairmatching.domain.Level.*;

import java.util.Arrays;
import pairmatching.message.ErrorMessage;

public enum Mission {
    CAR_RACE("자동차경주", LEVEL1),
    LOTTO("로또",LEVEL1),
    NUMBER_BASEBALL("숫자야구", LEVEL1),

    SHOPPING_BASKET("장바구니", LEVEL2),
    PAYMENT("결제", LEVEL2),
    SUBWAY("지하철노선도", LEVEL2),

    IMPROVEMENT("성능개선", LEVEL4),
    DEPLOYMENT("배포", LEVEL4),
    ;

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission findMission(String missionName, Level inputLevel) {
        return Arrays.stream(values())
                .filter(mission -> mission.name.equals(missionName) && mission.level.equals(inputLevel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_PAIR_INFO.getMessage()));
    }
}
