package vendingmachine.utils;

import vendingmachine.enums.ErrorMessage;

public class InputValidation {
    public static void validateRange(int value) {
        if (value < 10 || value % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
        }
    }

    public static int validateDigit(String input) {
        try {
            int value = Integer.parseInt(input);
            if (value < 100 || value % 10 != 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }

    public static int validate(String input) {
        int value = validateDigit(input);
        validateRange(value);
        return value;
    }
}
