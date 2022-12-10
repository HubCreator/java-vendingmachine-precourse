package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

import java.util.Objects;
import java.util.StringTokenizer;

public class Item {
    private final String itemName;
    private Price price;
    private Stock stock;

    public Item(String itemName, String price, String count) {
        this(itemName);
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
        return new Item(orderTokens.nextToken(), orderTokens.nextToken(), orderTokens.nextToken());
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
