package vendingmachine.view;

import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.VendingMachine;

import java.text.MessageFormat;

public class OutputView {
    private static final String message = "투입 금액: {0}원\n잔돈\n{1}";

    public static void printCoinStatus(CoinStatus input) {
        System.out.println(input.getStatus());
    }

    public static void printResult(VendingMachine vendingMachine) {
        System.out.println(MessageFormat.format(message,
                vendingMachine.getPurchaseAmount(),
                vendingMachine.getBalance()));
    }
}
