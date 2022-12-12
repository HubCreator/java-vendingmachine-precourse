package vendingmachine.dto.input;

public class ReadInputCoinDto {
    private final int vendingMachineCoin;

    public ReadInputCoinDto(int vendingMachineCoin) {
        this.vendingMachineCoin = vendingMachineCoin;
    }

    public int getVendingMachineCoin() {
        return vendingMachineCoin;
    }
}
