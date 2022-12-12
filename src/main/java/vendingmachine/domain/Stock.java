package vendingmachine.domain;

public class Stock {
    private final int stock;

    public Stock(String stock) {
        this.stock = validateDigit(stock);
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.message, exception);
        }
    }

    private enum ErrorMessage {
        INVALID_TYPE("상품의 가격은 숫자로 입력해야 합니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = errorHead + String.format(message, replaces);
        }
    }
}
