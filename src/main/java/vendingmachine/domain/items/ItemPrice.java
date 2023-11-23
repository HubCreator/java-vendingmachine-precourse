package vendingmachine.domain.items;

import vendingmachine.domain.Money;

public final class ItemPrice extends Money {

    private static final int MIN_PRICE = 100;

    public ItemPrice() {
        super("0");
    }

    public ItemPrice(String price) {
        super(price);
        validate(money);
    }

    private void validate(int value) {
        if (value < MIN_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE.message);
        }
    }

    private enum ErrorMessage {
        INVALID_PRICE("상품의 가격으로는 %d원 이상을 입력해야 합니다.", MIN_PRICE);

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = errorHead + String.format(message, replaces);
        }
    }
}
