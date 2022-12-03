package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {

    private VendingMachine vendingMachine;

    public Controller() {
        initVendingMachine();
    }

    public void run() {
        int purchaseAmount = InputView.readPurchaseAmount();
        vendingMachine.initializePurchaseAmount(purchaseAmount);
        
        while (vendingMachine.haveEnoughMoney(purchaseAmount)) {
            String itemName = InputView.readItemName(vendingMachine);
            vendingMachine.purchase(itemName);
        }

    }

    private void initVendingMachine() {
        int changeTotal = InputView.readVendingMachineChange();
        CoinStatus coinStatus = CoinStatus.create(changeTotal);
        OutputView.print(coinStatus);

        Items items = InputView.readItems();
        vendingMachine = VendingMachine.create(items, changeTotal);
    }
}
