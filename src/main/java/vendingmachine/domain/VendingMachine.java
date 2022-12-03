package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

public class VendingMachine {
    private final Items items;
    private final CoinStatus coinStatus;
    private int purchaseAmount;

    private VendingMachine(Items items, CoinStatus coinStatus) {
        this.items = items;
        this.coinStatus = coinStatus;
    }

    private VendingMachine(Items items) {
        this(items, null);
    }

    public static VendingMachine create(Items items) {
        return new VendingMachine(items);
    }

    public static VendingMachine create(Items items, CoinStatus coinStatus) {
        return new VendingMachine(items, coinStatus);
    }

    public boolean canPurchase(Item item) {
        return items.canPurchase(item);
    }

    public boolean haveBalance() {
        return items.isGreaterThanCheapestItem(purchaseAmount);
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public Item getItem(String input) {
        if (!items.canPurchase(new Item(input))) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_NAME.getMessage());
        }
        return new Item(input);
    }

    public String getBalance() {
        return coinStatus.getBalance(purchaseAmount);
    }

    public void purchase(Item item) {
        purchaseAmount -= items.purchase(item);
    }
}
