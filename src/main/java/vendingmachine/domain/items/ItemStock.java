package vendingmachine.domain.items;

public class ItemStock {
    private int stock;

    public ItemStock(String stock) {
        this.stock = validateDigit(stock);
    }

    public void decrease() {
        this.stock--;
    }

    public boolean hasStock() {
        return stock > 0;
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.message, exception);
        }
    }

    private enum ErrorMessage {
        INVALID_TYPE("금액과 수량은 숫자로 입력하셔야 합니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = errorHead + String.format(message, replaces);
        }
    }
}
