package vendingmachine.enums;

import java.util.HashMap;
import java.util.Map;

public enum Coin {
    COIN_500(500), COIN_100(100), COIN_50(50), COIN_10(10);

    private final int amount;
    private static final Map<Integer, Coin> cache = new HashMap<>();

    static {
        Coin[] values = values();
        for (Coin value : values) {
            cache.put(value.getAmount(), value);
        }
    }

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin getCoin(int randomIndex) {
        return findCoinByIndex(randomIndex);
    }

    private static Coin findCoinByIndex(int randomIndex) {
        return cache.get(randomIndex);
    }

    public int getAmount() {
        return amount;
    }
}

