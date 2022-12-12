package vendingmachine.domain;

public class Money {
    private static final int MIN_UNIT = 10;

    private final int money;

    public Money(int money) {
        this.money = validate(money);
    }

    private int validate(int change) {
        if (change % MIN_UNIT != 0) {
            throw new IllegalArgumentException("10 단위로 입력하셔야 합니다.");
        }
        return change;
    }
}
