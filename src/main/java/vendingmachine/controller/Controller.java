package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Item;
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
        mainLogic();
        OutputView.printResult(vendingMachine);
    }

    private void mainLogic() {
        while (vendingMachine.haveBalance()) {
            Item item = InputView.readItemName(vendingMachine);
            if (!vendingMachine.canPurchase(item)) {
                break;
            }
            vendingMachine.purchase(item);
        }
    }

    private VendingMachine initVendingMachine() {
        CoinStatus coinStatus = InputView.readVendingMachineChange();
        OutputView.printCoinStatus(coinStatus);

        Items items = InputView.readItems();
        int purchaseAmount = InputView.readPurchaseAmount();

        return VendingMachine.create(coinStatus, items, purchaseAmount);
    }
}
