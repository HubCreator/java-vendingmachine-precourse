package vendingmachine.domain;

import vendingmachine.domain.items.ItemPrice;

import java.util.Objects;


public class Money implements Comparable<Money>{
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

    public void decrease(ItemPrice itemPrice) {
        this.money -= itemPrice.money;
    }

    public void decrease(Coin coin) {
        this.money -= coin.getAmount();
    }

    public boolean isLowerOrEqual(Money inputMoney) {
        return this.money <= inputMoney.money;
    }


    public boolean isLowerThan(Coin coin) {
        return this.money < coin.getAmount();
    }

    public boolean isZero() {
        return this.money == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money target = (Money) o;
        return money == target.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public int compareTo(Money o) {
        return money - o.money;
    }


    private enum ErrorMessage {
        INVALID_UNIT("%d 단위로 입력하셔야 합니다.", MIN_UNIT),
        INVALID_TYPE("금액과 수량은 숫자로 입력하셔야 합니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = String.format(errorHead + message, replaces);
        }
    }
}

