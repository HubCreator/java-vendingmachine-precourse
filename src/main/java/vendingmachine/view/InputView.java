package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.Item;
import vendingmachine.domain.Items;
import vendingmachine.domain.VendingMachine;
import vendingmachine.utils.InputValidation;

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
        while (true) {
            try {
                printMessage(INPUT_VENDING_MACHINE_AMOUNT);
                return InputValidation.validateAmount(Console.readLine());
            } catch (IllegalArgumentException exception) {
                printMessage(exception);
            }
        }
    }

    /**
     * 상품 입력
     *
     * @return Items
     */
    public static Items readItems() {
        while (true) {
            try {
                printMessage(INPUT_ITEM_PRICE_COUNT);
                return InputValidation.validateItem(Console.readLine());
            } catch (IllegalArgumentException exception) {
                printMessage(exception);
            }
        }
    }

    /**
     * 투입 금액 입력
     *
     * @return int
     */
    public static int readPurchaseAmount() {
        while (true) {
            try {
                printMessage(INPUT_PURCHASE_AMOUNT);
                return InputValidation.validateAmount(Console.readLine());
            } catch (IllegalArgumentException exception) {
                printMessage(exception);
            }
        }
    }

    /**
     * 상품 입력
     *
     * @param vendingMachine
     * @return String
     */
    public static Item readItemName(VendingMachine vendingMachine) {
        while (true) {
            try {
                printMessage(String.format(PURCHASE_AMOUNT, vendingMachine.getPurchaseAmount()));
                printMessage(INPUT_PURCHASE_ITEM_NAME);
                String input = Console.readLine();
                return vendingMachine.getItem(input);
            } catch (IllegalArgumentException exception) {
                printMessage(exception);
            }
        }
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void printMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
