package vendingmachine.domain;

public class Item {
    private final String itemName;
    private final Price price;
    private final Stock stock;

    public Item(String itemName, String price, String stock) {
        this.itemName = itemName;
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
    }

    private Stock validateStock(String stock) {
        int value = validateDigit(stock);
        return new Stock(value);
    }

    private Price validatePrice(String price) {
        int value = validateDigit(price);
        if (value < 100 || value % 10 != 0) {
            throw new IllegalArgumentException("올바르지 않은 상품 입력 형식입니다.");
        }
        return new Price(value);
    }

    private int validateDigit(String price) {
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바르지 않은 상품 입력 형식입니다.", exception);
        }
    }
}
