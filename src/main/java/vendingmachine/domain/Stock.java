package vendingmachine.domain;

import vendingmachine.domain.error.ErrorMessage;

public class Stock {
    private final int stock;

    public Stock(String stock) {
        this.stock = validateDigit(stock);
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_INPUT_FORMAT.getMessage(), exception);
        }
    }
}
