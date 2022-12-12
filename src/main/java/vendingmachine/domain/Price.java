package vendingmachine.domain;

import vendingmachine.domain.error.ErrorMessage;

public class Price {

    public static final int MIN_PRICE = 100;
    public static final int MIN_UNIT = 10;

    private final int price;

    public Price(String price) {
        this.price = validate(price);
    }

    private int validate(String price) {
        int value = validateDigit(price);
        if (value < MIN_PRICE || value % MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_INPUT_FORMAT.getMessage());
        }
        return value;
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_INPUT_FORMAT.getMessage(), exception);
        }
    }
}
