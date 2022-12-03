package vendingmachine.utils;

import vendingmachine.domain.Item;
import vendingmachine.domain.Items;
import vendingmachine.enums.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

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

    public static Items validateItem(String input) {
        List<Item> result = new ArrayList<>();

        String[] split = input.split(";");
        for (String item : split) {
            validateItemFormat(item);
            item = item.substring(1, item.length() - 1);
            String[] information = item.split(",");
            result.add(new Item(information[0], Integer.parseInt(information[1]), Integer.parseInt(information[2])));
        }
        return new Items(result);
    }

    private static void validateItemFormat(String item) {
        if (item.charAt(0) != '[' || item.charAt(item.length() - 1) != ']') {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
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
