package vendingmachine.dto.input;

public class ReadItemNameDto {

    private final String itemName;

    public ReadItemNameDto(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
