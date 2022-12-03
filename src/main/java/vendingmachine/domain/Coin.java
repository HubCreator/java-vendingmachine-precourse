package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;
    private static final Map<Integer, Coin> coinMap = new HashMap<>();

    static {
        Coin[] values = values();
        for (Coin value : values) {
            coinMap.put(value.ordinal(), value);
        }
    }


    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin getRandomCoin(int randomIndex) {
        return coinMap.get(randomIndex);
    }

    public int getAmount() {
        return amount;
    }
}

