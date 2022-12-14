package vendingmachine.dto.input;

public final class ReadChangeDto {
    private final int vendingMachineCoin;

    public ReadChangeDto(final int vendingMachineCoin) {
        this.vendingMachineCoin = vendingMachineCoin;
    }

    public int getVendingMachineCoin() {
        return vendingMachineCoin;
    }
}
