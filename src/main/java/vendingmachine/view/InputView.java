package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.ReadInputCoinDto;

public class InputView {
    private InputView() {
    }

    private static class InputViewSingletonHelper {

        private static final InputView INPUT_VIEW = new InputView();
    }

    public static InputView getInstance() {
        return InputViewSingletonHelper.INPUT_VIEW;
    }

    public ReadInputCoinDto readVendingMachineCoin() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new ReadInputCoinDto(validateVendingMachineCoin(input));
    }

    private int validateVendingMachineCoin(String input) {
        int value = validateDigit(input);
        if (value % 10 != 0) {
            throw new IllegalArgumentException("10 단위로 입력하셔야 합니다.");
        }
        return value;
    }

    private int validateDigit(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력해야 합니다.", exception);
        }
    }
}
