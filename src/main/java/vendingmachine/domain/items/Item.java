package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.Objects;

public class Item {
    private final String itemName;
    private final ItemPrice itemPrice;
    private final ItemStock itemStock;

    public Item(String itemName, String price, String stock) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice(price);
        this.itemStock = new ItemStock(stock);
    }

    public Item(String itemName) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice();
        this.itemStock = new ItemStock();
    }

    public boolean isLowerPrice(Money money) {
        return itemPrice.isLowerThan(money);
    }

    public boolean hasStock() {
        return itemStock.hasStock();
    }

    public void purchase() {
        itemStock.decrease();
    }

    public ItemPrice getItemPrice() {
        return itemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }
}
