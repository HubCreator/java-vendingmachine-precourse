package vendingmachine.domain;

import vendingmachine.enums.ErrorMessage;

import java.util.Objects;

public class Item {
    private final String itemName;
    private Price price;
    private int count;

    public Item(String itemName, int price, int count) {
        this(itemName);
        validatePrice(price);
        this.price = new Price(price);
        this.count = count;
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    private void validatePrice(int price) {
        if ((price < PriceConditions.MIN.value) || (price % PriceConditions.UNIT.value != 0)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_INPUT_PRICE.getMessage());
        }
    }

    public Price purchase() {
        count--;
        return price;
    }

    public boolean haveStock() {
        return this.count > 0;
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
