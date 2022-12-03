package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {
    private final VendingMachine vendingMachine;

    public Controller() {
        this.vendingMachine = new VendingMachine();
    }

    public void run() {
        initVendingMachine();
        Items items = InputView.readItems();
        int amount = InputView.readPurchaseAmount();
        
    }

    private void initVendingMachine() {
        CoinStatus coinStatus = InputView.readVendingMachinePrice();
        if (coinStatus != null) {
            OutputView.print(coinStatus.getStatus());
        }
    }
}
