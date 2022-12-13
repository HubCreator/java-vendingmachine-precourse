package vendingmachine.domain.items;

import java.util.Objects;

public class ItemName implements Comparable<ItemName> {
    private final String itemName;

    public ItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemName itemName = (ItemName) o;
        return Objects.equals(this.itemName, itemName.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(itemName);
    }

    /*@Override
    public int compareTo(Item o) {
        return Objects.compare(this.itemPrice, o.itemPrice, Money::compareTo);
    }*/
    @Override
    public int compareTo(ItemName o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.itemName, o.itemName);
    }
}
