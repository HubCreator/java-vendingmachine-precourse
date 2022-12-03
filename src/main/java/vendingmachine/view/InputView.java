package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.CoinStatus;
import vendingmachine.enums.ErrorMessage;
import vendingmachine.validation.ValidationUtil;

public class InputView {

    private static final String INPUT_VENDING_MACHINE_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";

    public static CoinStatus readVendingMachinePrice() {
        System.out.println(INPUT_VENDING_MACHINE_AMOUNT);
        String input = Console.readLine();
        try {
            return ValidationUtil.getCoin(validateInput(input));
        } catch (IllegalArgumentException exception) {
            System.out.println(ErrorMessage.INVALID_FORMAT.getMessage());
            readVendingMachinePrice();
        }
        return null;
    }

    private static int validateInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
