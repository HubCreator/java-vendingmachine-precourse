package vendingmachine.dto.input;

public class ReadMoneyDto {
    private final int money;

    public ReadMoneyDto(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }
}
