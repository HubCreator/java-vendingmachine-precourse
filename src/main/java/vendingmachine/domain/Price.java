package vendingmachine.domain;

import vendingmachine.enums.ConstVaribale;
import vendingmachine.enums.ErrorMessage;

import java.util.Objects;

public class Price {
    private int price;

    public Price(String price) {
        this.price = validate(price);
    }

    public Price(int price) {
        this.price = price;
    }

    private static int validate(String price) {
        int result = toDigit(price);
        validateRange(result);
        return result;
    }

    private static void validateRange(int result) {
        if ((result < ConstVaribale.MIN_PRICE) || (result % ConstVaribale.MIN_UNIT != 0)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_INPUT_PRICE.getMessage());
        }
    }

    private static int toDigit(String price) {
        int result;
        try {
            result = Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
        return result;
    }

    public static Price getLowerPrice(Price price1, Price price2) {
        if (price1.price < price2.price) {
            return price1;
        }
        return price2;
    }

    public boolean isZero() {
        return price == 0;
    }

    public boolean isGreaterOrEqualThan(int amount) {
        return price >= amount;
    }

    public boolean isGreaterOrEqualThan(Price amount) {
        return this.price >= amount.price;
    }

    public void decrease(int amount) {
        this.price -= amount;
    }

    public void decrease(Price amount) {
        this.price -= amount.price;
    }

    public int get() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price target = (Price) o;
        return price == target.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
