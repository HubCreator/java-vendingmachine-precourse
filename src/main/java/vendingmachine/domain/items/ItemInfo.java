package vendingmachine.domain.items;

import vendingmachine.domain.Money;

public class ItemInfo {

    private final ItemPrice price;
    private final ItemStock stock;

    public ItemInfo(String price, String stock) {
        this.price = new ItemPrice(price);
        this.stock = new ItemStock(stock);
    }

    public boolean isLowerOrEqualPrice(Money inputMoney) {
        return price.isLowerOrEqualThan(inputMoney);
    }

    public boolean hasStock() {
        return stock.hasStock();
    }

    public ItemInfo purchase() {
        stock.decrease();
        return this;
    }

    public ItemPrice getPrice() {
        return price;
    }
}
