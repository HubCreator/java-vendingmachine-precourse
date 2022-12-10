package vendingmachine.enums;

public enum ErrorMessage {
    INVALID_RANGE_PRICE("올바르지 않은 금액이 입력되었습니다."),
    INVALID_RANGE_INPUT_PRICE("상품의 가격은 100원 이상이어야 하며, 10원으로 나누어 떨어져야 합니다."),
    IS_NOT_DIGIT("숫자를 입력해야 합니다."),
    INVALID_ITEM_NAME("해당 상품은 존재하지 않습니다."),
    INVALID_FORMAT("형식에 맞지 않는 입력입니다.");

    private static final String errorHead = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = errorHead + message;
    }

    public String getMessage() {
        return message;
    }
}
