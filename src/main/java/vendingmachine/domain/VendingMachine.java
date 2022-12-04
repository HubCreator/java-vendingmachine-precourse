package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

public class VendingMachine {
    private final Items items;
    private final CoinStatus coinStatus;
    private final Price purchase;

    private VendingMachine(Items items, CoinStatus coinStatus, int purchase) {
        this.items = items;
        this.coinStatus = coinStatus;
        this.purchase = new Price(purchase);
    }

    private VendingMachine(Items items) {
        this(items, null, 0);
    }

    /**
     * @param coinStatus     : 잔돈
     * @param items          : 구매 가능한 상품들
     * @param purchaseAmount : 투입 금액
     * @return VendingMachine
     */
    public static VendingMachine create(CoinStatus coinStatus, Items items, int purchaseAmount) {
        return new VendingMachine(items, coinStatus, purchaseAmount);
    }

    public boolean canPurchase(Item item) {
        return items.canPurchase(item);
    }

    public boolean haveBalance() {
        return items.isGreaterThanCheapestItem(purchase);
    }

    public int getPurchase() {
        return purchase.get();
    }

    public Item getItem(String input) {
        if (!items.canPurchase(new Item(input))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_NAME.getMessage());
        }
        return new Item(input);
    }

    public String getBalance() {
        return coinStatus.getBalance(purchase);
    }

    public void purchase(Item item) {
        purchase.decrease(items.purchase(item));
    }
}
