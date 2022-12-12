package vendingmachine.domain;

public class Item {
    private final String itemName;
    private final Price price;
    private final Stock stock;

    public Item(String itemName, String price, String stock) {
        this.itemName = itemName;
        this.price = new Price(price);
        this.stock = new Stock(stock);
    }
}
