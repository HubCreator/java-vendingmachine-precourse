package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.TreeMap;

public final class Items {
    private final TreeMap<Item, ItemStock> itemMap;

    public Items(TreeMap<Item, ItemStock> itemMap) {
        this.itemMap = itemMap;
    }

    public boolean haveEnoughMoney(Money inputMoney) {
        ItemPrice itemPrice = itemMap.firstKey().getItemPrice();
        return itemPrice.isLowerOrEqual(inputMoney);
    }

    public boolean canPurchase(Item item, Money inputMoney) {
        if (!itemMap.containsKey(item)) {
            throw new IllegalArgumentException(ErrorMessage.ITEM_NOT_FOUNT.message);
        }
        return itemMap.get(item).hasStock() && itemMap.floorKey(item).getItemPrice().isLowerOrEqual(inputMoney);
    }

    public Item purchase(Item item) {
        itemMap.get(item).decrease();
        return itemMap.floorKey(item);
    }

    private enum ErrorMessage {
        ITEM_NOT_FOUNT("해당 상품이 없습니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message) {
            this.message = errorHead + message;
        }
    }
}
