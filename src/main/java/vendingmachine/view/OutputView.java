package vendingmachine.view;

import vendingmachine.domain.CoinStatus;

public class OutputView {
    public static void print(CoinStatus input) {
        System.out.println(input.getStatus());
    }
}
