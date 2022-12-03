package vendingmachine;

import java.util.Objects;

public class Item {
    private final String itemName;
    private final int price;
    private final int count;

    public Item(String itemName, int price, int count) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
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
        return price == item.price && count == item.count && Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, price, count);
    }

}
