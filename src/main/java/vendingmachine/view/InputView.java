package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.input.ReadItemNameDto;
import vendingmachine.dto.input.ReadItemsInfoDto;
import vendingmachine.dto.input.ReadChangeDto;
import vendingmachine.dto.input.ReadMoneyDto;

public final class InputView {

    public static final char PREFIX = '[';
    public static final char SUFFIX = ']';
    public static final String ITEMS_DELIMITER = ";";
    public static final String ITEM_INFOS_DELIMITER = ",";

    private InputView() {
    }

    private static class InputViewSingletonHelper {
        private static final InputView INPUT_VIEW = new InputView();
    }

    public static InputView getInstance() {
        return InputViewSingletonHelper.INPUT_VIEW;
    }

    public ReadChangeDto readChange() {
        printViewMessage(ViewMessage.INPUT_CHANGE);
        String input = Console.readLine();

        return new ReadChangeDto(validateDigit(input));
    }

    public ReadItemsInfoDto readItemsInfo() {
        printViewMessage(ViewMessage.INPUT_READ_ITEMS_INFO);
        String input = Console.readLine();

        return new ReadItemsInfoDto(validateFormat(input));
    }

    public ReadMoneyDto readMoney() {
        printViewMessage(ViewMessage.INPUT_MONEY);
        String input = Console.readLine();

        return new ReadMoneyDto(validateDigit(input));
    }

    public ReadItemNameDto readItemName() {
        printViewMessage(ViewMessage.INPUT_ITEM_NAME);
        String input = Console.readLine();

        return new ReadItemNameDto(input);
    }

    private String validateFormat(String input) {
        String[] items = validateItemsFormat(input);
        validateSingleItemFormat(items);

        return input;
    }

    private String[] validateItemsFormat(String input) {
        String[] items = input.split(ITEMS_DELIMITER);
        for (String item : items) {
            if (item.charAt(0) != PREFIX || item.charAt(item.length() - 1) != SUFFIX) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_FORMAT.message);
            }
        }

        return items;
    }

    private void validateSingleItemFormat(String[] items) {
        for (String item : items) {
            String[] infos = item.split(ITEM_INFOS_DELIMITER);
            if (infos.length != 3) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ITEM_FORMAT.message);
            }
        }
    }

    private int validateDigit(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TYPE.message, exception);
        }
    }

    private void printViewMessage(ViewMessage viewMessage, Object... replaces) {
        if (replaces.length > 0) {

        }
        System.out.println(viewMessage.message);
    }

    private enum ViewMessage {
        INPUT_CHANGE("자판기가 보유하고 있는 금액을 입력해 주세요."),
        INPUT_READ_ITEMS_INFO("상품명과 가격, 수량을 입력해 주세요.\n"),
        INPUT_MONEY("투입 금액을 입력해 주세요."),
        INPUT_ITEM_NAME("구매할 상품명을 입력해 주세요.");

        private final String message;

        ViewMessage(String message) {
            this.message = message;
        }
    }


    private enum ErrorMessage {
        INVALID_TYPE("상품의 가격은 숫자로 입력해야 합니다."),
        INVALID_ITEM_FORMAT("올바르지 않은 형식의 상품 입력입니다.");

        private static final String errorHead = "[ERROR] ";
        private final String message;

        ErrorMessage(String message, Object... replaces) {
            this.message = errorHead + String.format(message, replaces);
        }
    }
}
