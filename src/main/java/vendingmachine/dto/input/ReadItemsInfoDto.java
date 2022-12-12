package vendingmachine.dto.input;

public class ReadItemsInfoDto {
    private final String itemsInfo;

    public ReadItemsInfoDto(String itemsInfo) {
        this.itemsInfo = itemsInfo;
    }

    public String getItemsInfo() {
        return itemsInfo;
    }
}
