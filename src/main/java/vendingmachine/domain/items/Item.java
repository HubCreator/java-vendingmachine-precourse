package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.Objects;

public class Item implements Comparable<Item> {
    private final String itemName;
    private final ItemPrice itemPrice;

    public Item(String itemName, String price) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice(price);
    }

    public Item(String itemName) {
        this.itemName = itemName;
        this.itemPrice = new ItemPrice();
    }

    public boolean isLowerOrEqualPrice(Money money) {
        return itemPrice.isLowerOrEqualThan(money);
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

    @Override
    public int compareTo(Item o) {
        return Objects.compare(this.itemPrice, o.itemPrice, Money::compareTo);
    }
}
