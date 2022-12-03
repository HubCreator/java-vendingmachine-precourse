package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

import java.util.List;

public class Items {
    private final List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public boolean has(Item item) {
        if (!items.contains(item)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_NAME.getMessage());
        }
        return true;
    }

    public int purchase(Item item) {
        int itemIndex = items.indexOf(item);
        return items.get(itemIndex).purchase();
    }

    public boolean canPurchase(String itemName) {
        int itemIndex = items.indexOf(new Item(itemName));
        Item item = items.get(itemIndex);
        return item.haveStock();
    }

    public boolean isLowerThanCheapestOne(int amount) {
        int minPrice = getMinPrice();
        return minPrice > amount;
    }

    private int getMinPrice() {
        int minPrice = Integer.MAX_VALUE;
        for (Item item : items) {
            minPrice = item.getIfLowerThan(minPrice);
        }
        return minPrice;
    }
}
