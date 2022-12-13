package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.TreeMap;

public class Items {
    private final TreeMap<ItemName, ItemInfo> itemMap;

    public Items(TreeMap<ItemName, ItemInfo> itemMap) {
        this.itemMap = itemMap;
    }

    public boolean canBuySomething(ItemName itemName, Money inputMoney) {
        if (!itemMap.containsKey(itemName)) {
            throw new IllegalArgumentException("해당 상품이 없습니다.");
        }
        return itemMap.get(itemMap.firstKey()).isLowerOrEqualPrice(inputMoney);
    }

    public boolean canPurchase(ItemName itemName, Money inputMoney) {
        return itemMap.get(itemName).hasStock() && itemMap.get(itemName).isLowerOrEqualPrice(inputMoney);
    }

    public ItemInfo purchase(ItemName itemName) {
        itemMap.put(itemName, itemMap.get(itemName).purchase());
        return itemMap.get(itemName);
    }
}
