package vendingmachine.dto.input;

public final class ReadItemNameDto {

    private final String itemName;

    public ReadItemNameDto(final String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
