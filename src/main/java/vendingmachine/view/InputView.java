package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.enums.ErrorMessage;
import vendingmachine.utils.InputValidation;
import vendingmachine.utils.Util;

import java.text.MessageFormat;

public class InputView {

    private static final String INPUT_VENDING_MACHINE_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static final String INPUT_ITEM_PRICE_COUNT = "상품명과 가격, 수량을 입력해 주세요.";
    public static final String INPUT_PURCHASE_AMOUNT = "투입 금액을 입력해 주세요.";
    public static final String PURCHASE_AMOUNT = "투입 금액: {0}원";
    public static final String INPUT_PURCHASE_ITEM_NAME = "구매할 상품명을 입력해 주세요.";

    public static int readVendingMachineChange() {
        System.out.println(INPUT_VENDING_MACHINE_AMOUNT);
        String input = Console.readLine();
        try {
            return InputValidation.validate(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            readVendingMachineChange();
        }
        return -1;
    }

    public static Items readItems() {
        System.out.println(INPUT_ITEM_PRICE_COUNT);
        String input = Console.readLine();
        try {
            return new Items(Util.getItems(input));
        } catch (IllegalArgumentException exception) {
            System.out.println(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            readItems();
        }
        return null;
    }

    public static int readPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return InputValidation.validateDigit(input);
    }

    public static String readItemName(VendingMachine vendingMachine) {
        System.out.println(MessageFormat.format(PURCHASE_AMOUNT, vendingMachine.getPurchaseAmount()));
        System.out.println(INPUT_PURCHASE_ITEM_NAME);
        String input = Console.readLine();
        if (!vendingMachine.hasItem(input)) {
            readItemName(vendingMachine);
        }
        return input;
    }
}
