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

        String itemName;
        do {
            itemName = InputView.readItemName(vendingMachine);
        } while (vendingMachine.canPurchase(itemName));
        OutputView.printResult(vendingMachine);

    }

    private void initVendingMachine() {
        int changeTotal = InputView.readVendingMachineChange();
        CoinStatus coinStatus = CoinStatus.create(changeTotal);
        OutputView.printCoinStatus(coinStatus);

        Items items = InputView.readItems();
        vendingMachine = VendingMachine.create(items, coinStatus);
    }
}
