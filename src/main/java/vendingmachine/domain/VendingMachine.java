package vendingmachine.domain;

public class VendingMachine {
    private final Items items;
    private int changeTotal;
    private int purchaseAmount;

    private VendingMachine(Items items, int changeTotal) {
        this.items = items;
        this.changeTotal = changeTotal;
    }

    public static VendingMachine create(Items items, int changeTotal) {
        return new VendingMachine(items, changeTotal);
    }

    public void purchase(String itemName) {
        purchaseAmount -= items.purchase(new Item(itemName));
    }

    public void initializePurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public boolean haveEnoughMoney(int purchaseAmount) {
        return purchaseAmount > changeTotal;
    }

    public int getChangeTotal() {
        return changeTotal;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public boolean hasItem(String input) {
        return items.has(new Item(input));
    }
}
