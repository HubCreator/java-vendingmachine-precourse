package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {

    public void run() {
        CoinStatus coinStatus = InputView.readVendingMachinePrice();
        OutputView.print(coinStatus.getStatus());

        Items items = InputView.readItems();
        int amount = InputView.readPurchaseAmount();
    }
}
