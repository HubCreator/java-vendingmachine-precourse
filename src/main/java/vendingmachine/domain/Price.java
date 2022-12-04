package vendingmachine.domain;

public class Price {
    private int price;

    public Price(int price) {
        this.price = price;
    }

    public boolean isZero() {
        return price == 0;
    }

    public boolean isGreaterOrEqualThan(int amount) {
        return price >= amount;
    }

    public void decrease(int amount) {
        this.price -= amount;
    }

    public int get() {
        return price;
    }
}
