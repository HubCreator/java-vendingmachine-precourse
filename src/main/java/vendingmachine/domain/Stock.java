package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

public class Stock {
    private int stock;

    public Stock(String stock) {
        this.stock = validate(stock);
    }

    private int validate(String stock) {
        try {
            return Integer.parseInt(stock);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }

    public void decrease() {
        stock--;
    }

    public boolean haveStock() {
        return stock > 0;
    }
}
