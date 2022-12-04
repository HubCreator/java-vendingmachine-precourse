package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

import java.util.List;

public class Items {
    private final List<Item> items;
    private final int minPrice;

    public Items(List<Item> items) {
        this.items = items;
        this.minPrice = getMinPrice();
    }

    public int purchase(Item item) {
        int itemIndex = items.indexOf(item);
        return items.get(itemIndex).purchase();
    }

    public boolean canPurchase(Item item) {
        if (!items.contains(item)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_NAME.getMessage());
        }
        int index = items.indexOf(item);
        return items.get(index).haveStock();
    }

    public boolean isGreaterThanCheapestItem(Price amount) {
        if (amount.isZero()) {
            return false;
        }
        return amount.isGreaterOrEqualThan(minPrice);
    }

    private int getMinPrice() {
        int minPrice = Integer.MAX_VALUE;
        for (Item item : items) {
            minPrice = item.getLowerOne(minPrice);
        }
        return minPrice;
    }
}
