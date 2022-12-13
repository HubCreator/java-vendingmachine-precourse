package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.TreeMap;

public class Items {
    private final TreeMap<Item, Integer> itemMap;

    public Items(TreeMap<Item, Integer> itemMap) {
        this.itemMap = itemMap;
    }

    public boolean canPurchase(Item item, Money inputMoney) {
        if (!itemMap.containsKey(item)) {
            throw new IllegalArgumentException("해당 상품이 없습니다.");
        }
        if (itemMap.firstKey().isLowerOrEqualPrice(inputMoney)) {
            return false;
        }
        return true;
    }

    public void purchase(Item item) {
        itemMap.put(item, itemMap.get(item) - 1);
        if (itemMap.get(item) == 0) {
            itemMap.remove(item);
        }
    }
}
