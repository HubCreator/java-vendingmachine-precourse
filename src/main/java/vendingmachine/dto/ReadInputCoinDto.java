package vendingmachine.dto;

public class ReadInputCoinDto {
    private final int vendingMachineCoin;

    public ReadInputCoinDto(int vendingMachineCoin) {
        this.vendingMachineCoin = vendingMachineCoin;
    }

    public int getVendingMachineCoin() {
        return vendingMachineCoin;
    }
}
