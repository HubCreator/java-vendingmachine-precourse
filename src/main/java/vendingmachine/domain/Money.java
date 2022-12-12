package vendingmachine.domain;

import vendingmachine.domain.items.ItemPrice;

public class Money {
    private static final int MIN_UNIT = 10;

    private int money;

    public Money(int money) {
        this.money = validate(money);
    }

    private int validate(int change) {
        if (change % MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT.message);
        }
        return change;
    }

    public int getMoney() {
        return money;
    }

    public boolean isLowerThan(Money target) {
        return this.money < target.money;
    }

    public void decrease(ItemPrice itemPrice) {
        decrease(itemPrice.getPrice());
    }

    private void decrease(Money price) {
        this.money -= price.money;
    }

    private enum ErrorMessage {
        INVALID_UNIT("%d 단위로 입력하셔야 합니다.", MIN_UNIT),;

        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = String.format(message, replaces);
        }
    }
}

