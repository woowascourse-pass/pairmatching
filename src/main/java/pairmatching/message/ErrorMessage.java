package pairmatching.message;

public enum ErrorMessage {
    INVALID_INPUT("[ERROR] 입력 값은 1,2,3,Q만 입력 가능합니다."),
    INVALID_PAIR_INFO("[ERROR] 입력 정보가 잘못되었습니다."),
    PAIR_MATCH_FAILED("[ERROR] 매칭에 실패했습니다."),
    INVALID_REMATCH("[ERROR] 입력은 예, 아니오만 가능합니다."),
    NO_PAIR_EXISTS("[ERROR] 매칭 이력이 없습니다.")
    ;

    private final String description;

    ErrorMessage(String description) {
        this.description = description;
    }

    public String getMessage() {
        return description;
    }
}
