package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.CoinStatus;
import vendingmachine.domain.Items;
import vendingmachine.enums.ErrorMessage;
import vendingmachine.validation.ValidationUtil;

public class InputView {

    private static final String INPUT_VENDING_MACHINE_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static final String INPUT_ITEM_PRICE_COUNT = "상품명과 가격, 수량을 입력해 주세요.";
    public static final String INPUT_PURCHASE_AMOUNT = "투입 금액을 입력해 주세요.";

    public static CoinStatus readVendingMachinePrice() {
        System.out.println(INPUT_VENDING_MACHINE_AMOUNT);
        String input = Console.readLine();
        try {
            int value = validateDigit(input);
            validateRange(value);
            return CoinStatus.create(value);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            readVendingMachinePrice();
        }
        return null;
    }

    private static void validateRange(int value) {
        if (value < 10 || value % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
        }
    }

    private static int validateDigit(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value < 100 || value % 10 != 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public static Items readItems() {
        System.out.println(INPUT_ITEM_PRICE_COUNT);
        String input = Console.readLine();
        try {
            return new Items(ValidationUtil.getItems(input));
        } catch (IllegalArgumentException exception) {
            System.out.println(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            readItems();
        }
        return null;
    }

    public static int readPurchaseAmount() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
        String input = Console.readLine();
        return validateDigit(input);
    }
}
