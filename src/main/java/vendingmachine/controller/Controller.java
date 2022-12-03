package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.view.InputView;

public class Controller {

    public void run() {
        CoinStatus coinStatus = InputView.readVendingMachinePrice();
        System.out.println(coinStatus.printStatus());
    }
}
