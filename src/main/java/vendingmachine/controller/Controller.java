package vendingmachine.controller;

import vendingmachine.domain.CoinStatus;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {

    public void run() {
        CoinStatus coinStatus = InputView.readVendingMachinePrice();
        OutputView.print(coinStatus.printStatus());
        
    }
}
