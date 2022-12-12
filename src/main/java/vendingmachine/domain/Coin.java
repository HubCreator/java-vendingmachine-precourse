package vendingmachine.domain;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin map(int coin) {
        if (COIN_500.amount == coin) {
            return COIN_500;
        }
        if (COIN_100.amount == coin) {
            return COIN_100;
        }
        if (COIN_50.amount == coin) {
            return COIN_50;
        }
        if (COIN_10.amount == coin) {
            return COIN_10;
        }
        return null;
    }

    // 추가 기능 구현
    public int getAmount() {
        return amount;
    }
}
