package vendingmachine.domain.items;

import vendingmachine.domain.Money;

import java.util.List;

public class Items {
    private final List<Item> items;

    public Items(List<Item> items) {
        this.items = items;
    }

    public Item canPurchase(Item item, Money inputMoney) {
        return items.stream()
                .filter(m -> m.equals(item) && m.hasStock() && m.isLowerPrice(inputMoney))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));
    }
}
