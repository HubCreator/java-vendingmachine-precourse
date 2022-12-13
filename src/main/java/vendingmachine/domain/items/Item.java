package vendingmachine.domain.items;

import java.util.Objects;

public class Item implements Comparable<Item> {
    private final String itemName;
    private final ItemPrice itemPrice;

    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice(itemPrice);
    }

    public Item(String itemName) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice();
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
        return Objects.equals(this.itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(itemName);
    }

    @Override
    public int compareTo(Item o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.itemName, o.itemName);
    }
}
