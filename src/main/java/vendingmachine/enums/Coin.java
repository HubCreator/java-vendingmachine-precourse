package vendingmachine.enums;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin getRandomCoin(int randomIndex) {
        Coin[] values = values();
        for (Coin value : values) {
            if (value.ordinal() == randomIndex) {
                return value;
            }
        }
        return null;
    }

    public int getAmount() {
        return amount;
    }

    public static String printCurrentCoinTotal() {
        StringBuilder result = new StringBuilder();
        Coin[] values = values();
        for (Coin value : values) {
        }

        return result.toString();
    }
}

