package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.input.ReadItemsInfoDto;
import vendingmachine.dto.input.ReadChangeDto;

public class InputView {
    private InputView() {
    }

    private static class InputViewSingletonHelper {
        private static final InputView INPUT_VIEW = new InputView();
    }

    public static InputView getInstance() {
        return InputViewSingletonHelper.INPUT_VIEW;
    }

    public ReadChangeDto readChange() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String input = Console.readLine();
        return new ReadChangeDto(validateChange(input));
    }

    public ReadItemsInfoDto readItemsInfo() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.\n");
        String input = Console.readLine();

        return new ReadItemsInfoDto(validateFormat(input));
    }

    private String validateFormat(String input) {
        String[] items = validateItemsFormat(input);
        validateSingleItemFormat(items);
        return input;
    }

    private String[] validateItemsFormat(String input) {
        String[] items = input.split(";");
        for (String item : items) {
            if (item.charAt(0) != '[' || item.charAt(item.length() - 1) != ']') {
                throw new IllegalArgumentException("올바르지 않은 상품 입력 형식입니다.");
            }
        }
        return items;
    }

    private void validateSingleItemFormat(String[] items) {
        for (String item : items) {
            String[] infos = item.split(",");
            if (infos.length != 3) {
                throw new IllegalArgumentException("올바르지 않은 상품 입력 형식입니다.");
            }
        }
    }

    private int validateChange(String input) {
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
