package vendingmachine.enums;

public enum ErrorMessage {
    INVALID_RANGE_PRICE("올바르지 않은 금액이 입력되었습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
