package vendingmachine.dto.input;

public final class ReadMoneyDto {
    private final int money;

    public ReadMoneyDto(final int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
