package pairmatching.message;

public enum ErrorMessage {
    BLANK_ERROR_MESSAGE("[ERROR] 공백만 입력 되었습니다."),
    INVALID_ERROR_MESSAGE("[ERROR] 유효하지 않은 입력값입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
