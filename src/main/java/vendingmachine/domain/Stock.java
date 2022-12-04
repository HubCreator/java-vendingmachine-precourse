package vendingmachine.domain;

public class Stock {
    private int stock;

    public Stock(int stock) {
        this.stock = stock;
    }

    public void decrease() {
        stock--;
    }

    public boolean haveStock() {
        return stock > 0;
    }

}
