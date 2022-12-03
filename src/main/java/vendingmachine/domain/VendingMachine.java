package vendingmachine.domain;

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

    public boolean canPurchase(String itemName) {
        if (items.isGreaterThanCheapestItem(purchaseAmount) && items.canPurchase(itemName)) {
            purchaseAmount -= items.purchase(new Item(itemName));
            return true;
        }
        return false;
    }

    public void initializePurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public boolean hasItem(String input) {
        return items.has(new Item(input));
    }

    public String getBalance() {
        return coinStatus.getBalance(purchaseAmount);
    }
}
