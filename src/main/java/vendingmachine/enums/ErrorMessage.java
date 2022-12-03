package vendingmachine.enums;

public enum ErrorMessage {
    INVALID_RANGE_PRICE("올바르지 않은 금액이 입력되었습니다."),
    INVALID_FORMAT("숫자를 입력해야 합니다.");

    private static final String errorHead = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = errorHead + message;
    }

    public String getMessage() {
        return message;
    }
}
