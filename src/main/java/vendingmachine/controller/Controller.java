package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {

    private final VendingMachine vendingMachine;

    public Controller() {
        this.vendingMachine = initVendingMachine();
    }

    public void run() {
        vendingMachine.initializePurchaseAmount(InputView.readPurchaseAmount());
        mainLogic();
        OutputView.printResult(vendingMachine);
    }

    private void mainLogic() {
        String itemName;
        do {
            itemName = InputView.readItemName(vendingMachine);
        } while (vendingMachine.canPurchase(itemName));
    }

    private VendingMachine initVendingMachine() {
        int changeTotal = InputView.readVendingMachineChange();
        CoinStatus coinStatus = CoinStatus.create(changeTotal);
        OutputView.printCoinStatus(coinStatus);

        Items items = InputView.readItems();
        return VendingMachine.create(items, coinStatus);
    }
}
