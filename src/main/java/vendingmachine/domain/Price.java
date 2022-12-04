package vendingmachine.domain;

import java.util.Objects;

public class Price {
    private int price;

    public Price(int price) {
        this.price = price;
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
