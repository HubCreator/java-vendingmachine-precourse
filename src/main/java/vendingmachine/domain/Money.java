package vendingmachine.domain;

import vendingmachine.domain.items.ItemPrice;

public class Money {
    private static final int MIN_UNIT = 10;

    protected int money;

    public Money(String money) {
        int value = validateDigit(money);
        this.money = validate(value);
    }

    public Money(int money) {
        this.money = validate(money);
    }

    private int validate(int input) {
        if (input % MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_UNIT.message);
        }
        return input;
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.message, exception);
        }
    }

    public int getMoney() {
        return money;
    }

  /*  public boolean isLowerThan(Money target) {
        return money < target.money;
    }*/

    public void decrease(ItemPrice itemPrice) {
        this.money -= itemPrice.money;
    }

    private enum ErrorMessage {
        INVALID_UNIT("%d 단위로 입력하셔야 합니다.", MIN_UNIT),
        INVALID_TYPE("금액은 숫자로 입력하셔야 합니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = String.format(errorHead + message, replaces);
        }
    }
}

