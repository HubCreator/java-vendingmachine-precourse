package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.InputValidation;
import vendingmachine.utils.Util;

public class InputView {

    private static final String INPUT_VENDING_MACHINE_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static final String INPUT_ITEM_PRICE_COUNT = "상품명과 가격, 수량을 입력해 주세요.";
    public static final String INPUT_PURCHASE_AMOUNT = "투입 금액을 입력해 주세요.";
    public static final String PURCHASE_AMOUNT = "투입 금액: %d원";
    public static final String INPUT_PURCHASE_ITEM_NAME = "구매할 상품명을 입력해 주세요.";

    /**
     * 잔돈 입력
     *
     * @return int
     */
    public static int readVendingMachineChange() {
        printMessage(INPUT_VENDING_MACHINE_AMOUNT);
        String input = Console.readLine();
        try {
            return InputValidation.validateInputChange(input);
        } catch (IllegalArgumentException exception) {
            printMessage(exception);
            readVendingMachineChange();
        }
        return -1;
    }

    /**
     * 상품 입력
     *
     * @return Items
     */
    public static Items readItems() {
        printMessage(INPUT_ITEM_PRICE_COUNT);
        String input = Console.readLine();
        try {
            InputValidation.validateItem(input);
            return new Items(Util.getItems(input));
        } catch (IllegalArgumentException exception) {
            printMessage(exception);
            readItems();
        }
        return null;
    }

    /**
     * 투입 금액 입력
     *
     * @return int
     */
    public static int readPurchaseAmount() {
        printMessage(INPUT_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return InputValidation.validateDigit(input);
    }

    /**
     * 상품 입력
     *
     * @param vendingMachine
     * @return String
     */
    public static String readItemName(VendingMachine vendingMachine) {
        printMessage(String.format(PURCHASE_AMOUNT, vendingMachine.getPurchaseAmount()));
        printMessage(INPUT_PURCHASE_ITEM_NAME);
        String input = Console.readLine();
        if (!vendingMachine.hasItem(input)) {
            readItemName(vendingMachine);
        }
        return input;
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void printMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
