package vendingmachine.utils;

import vendingmachine.enums.ErrorMessage;

public class InputValidation {

    public static final int MIN_PRICE = 100;
    public static final int MIN_UNIT = 10;

    public static int validateInputChange(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value < MIN_PRICE || value % MIN_UNIT != 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }

    public static void validateItem(String input) {
        String[] split = input.split(";");
        for (String item : split) {
            if (item.charAt(0) != '[' || item.charAt(item.length() - 1) != ']') {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }
        }
    }

    public static int validateDigit(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value < MIN_PRICE || value % MIN_UNIT != 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }
}
