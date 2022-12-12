package vendingmachine.domain.items;

import vendingmachine.domain.Money;

public class ItemPrice {

    private static final int MIN_PRICE = 100;
    private static final int MIN_UNIT = 10;

    private final Money price;

    public ItemPrice() {
        this.price = new Money(0);
    }

    public ItemPrice(String price) {
        this.price = new Money(validate(price));
    }

    private int validate(String price) {
        int value = validateDigit(price);
        if (value < MIN_PRICE ) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE.message);
        }
        if (value % MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT.message);
        }
        return value;
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.message, exception);
        }
    }

    public boolean isLowerThan(Money target) {
        return this.price.isLowerThan(target);
    }

    public Money getPrice() {
        return price;
    }

    private enum ErrorMessage {
        INVALID_PRICE("상품의 가격으로는 %d원 이상을 입력해야 합니다.", MIN_PRICE),
        INVALID_UNIT("상품의 가격은 %d원 단위어야 합니다.", MIN_UNIT),
        INVALID_TYPE("상품의 가격은 숫자로 입력해야 합니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = errorHead + String.format(message, replaces);
        }
    }

}
