package vendingmachine.dto.input;

public class ReadChangeDto {
    private final int vendingMachineCoin;

    public ReadChangeDto(int vendingMachineCoin) {
        this.vendingMachineCoin = vendingMachineCoin;
    }

    public int getVendingMachineCoin() {
        return vendingMachineCoin;
    }
}
