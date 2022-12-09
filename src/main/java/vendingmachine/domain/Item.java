package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

import java.util.Objects;
import java.util.StringTokenizer;

public class Item {
    private final String itemName;
    private Price price;
    private Stock stock;

    public Item(String itemName, int price, int count) {
        this(itemName);
        validatePrice(price);
        this.price = new Price(price);
        this.stock = new Stock(count);
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public static Item createOrder(String order) {
        return validate(order);
    }

    private static Item validate(String order) {
        if (order.charAt(0) != '[' || order.charAt(order.length() - 1) != ']') {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }

        StringTokenizer orderTokens = new StringTokenizer(order, "[],");
        if (orderTokens.countTokens() != 3) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        String itemName = orderTokens.nextToken();
        int price = validatePrice(orderTokens.nextToken());
        int stock = validateStock(orderTokens.nextToken());
        return new Item(itemName, price, stock);
    }

    private static int validatePrice(String price) {
        int result;
        try {
            result = Integer.parseInt(price);
            validatePrice(result);
            return result;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }

    private static void validatePrice(int price) {
        if ((price < PriceConditions.MIN.value) || (price % PriceConditions.UNIT.value != 0)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_INPUT_PRICE.getMessage());
        }
    }

    private static int validateStock(String stock) {
        try {
            return Integer.parseInt(stock);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage(), exception);
        }
    }

    public Price purchase() {
        stock.decrease();
        return price;
    }

    public boolean haveStock() {
        return stock.haveStock();
    }

    public Price getLowerOne(Price price) {
        return Price.getLowerPrice(this.price, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }

    private enum PriceConditions {
        MIN(100), UNIT(10);

        private final int value;

        PriceConditions(int value) {
            this.value = value;
        }
    }
}
