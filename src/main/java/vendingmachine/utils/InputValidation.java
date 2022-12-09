package vendingmachine.utils;

import vendingmachine.domain.Item;
import vendingmachine.domain.Items;
import vendingmachine.enums.ConstVaribale;
import vendingmachine.enums.ErrorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InputValidation {

    private static final String ITEM_DELIMITER = ";";

    public static int isDigit(String input) {
        try {
            int value = Integer.parseInt(input);
            validateRange(value);
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.IS_NOT_DIGIT.getMessage(), exception);
        }
    }

    private static void validateRange(int value) {
        if (value < ConstVaribale.MIN_PRICE || value % ConstVaribale.MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
        }
    }

    private static void validateUnit(int value) {
        if ( value % ConstVaribale.MIN_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_PRICE.getMessage());
        }
    }


    public static Items validateItem(String input) {
        List<Item> result = new ArrayList<>();

        StringTokenizer tokens = new StringTokenizer(input, ITEM_DELIMITER);
        if (tokens.countTokens() == 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        while (tokens.hasMoreTokens()) {
            result.add(Item.createOrder(tokens.nextToken()));
        }
        return new Items(result);
    }

    public static int isValidInputAmount(String input) {
        int digit = isDigit(input);
        validateUnit(digit);
        return digit;
    }
}
