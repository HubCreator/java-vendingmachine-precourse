package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {

    private final VendingMachine vendingMachine;

    public Controller() {
        int changeTotal = initVendingMachine();
        Items items = InputView.readItems();
        this.vendingMachine = VendingMachine.create(items, changeTotal);
    }

    public void run() {
        int purchaseAmount = InputView.readPurchaseAmount();
        vendingMachine.initializePurchaseAmount(purchaseAmount);
        while (vendingMachine.haveEnoughMoney(purchaseAmount)) {
            String itemName = InputView.readItemName(vendingMachine);
            vendingMachine.purchase(itemName);
        }

    }

    private int initVendingMachine() {
        CoinStatus coinStatus = InputView.readVendingMachineChange();
        if (coinStatus != null) {
            OutputView.print(coinStatus.getStatus());
        }
        return coinStatus.getAmountTotal();
    }
}
