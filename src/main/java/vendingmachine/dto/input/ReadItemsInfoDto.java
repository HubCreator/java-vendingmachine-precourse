package vendingmachine.dto.input;

public final class ReadItemsInfoDto {
    private final String itemsInfo;

    public ReadItemsInfoDto(final String itemsInfo) {
        this.itemsInfo = itemsInfo;
    }

    public String getItemsInfo() {
        return itemsInfo;
    }
}
