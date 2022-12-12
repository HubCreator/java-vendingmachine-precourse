package vendingmachine.domain.items;

public class Item {
    private final String itemName;
    private final ItemPrice itemPrice;
    private final ItemStock itemStock;

    public Item(String itemName, String price, String stock) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice(price);
        this.itemStock = new ItemStock(stock);
    }
}
