package vendingmachine.domain.error;

public enum ErrorMessage {
    INVALID_ITEM_INPUT_FORMAT("올바르지 않은 상품 입력 형식입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
