package vendingmachine.domain;

import vendingmachine.enums.Coin;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoinStatus implements Iterable<Coin> {

    private static final String messageFormat = "{0}원 - {1}개\n";
    private static final Map<Coin, Integer> coinMap = new EnumMap<>(Coin.class);

    static {
        Coin[] values = Coin.values();
        for (Coin value : values) {
            coinMap.put(value, 0);
        }
    }

    public CoinStatus(List<Coin> coins) {
        for (Coin coin : coins) {
            coinMap.put(coin, coinMap.get(coin) + 1);
        }
    }

    public String printStatus() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Coin, Integer> e : coinMap.entrySet()) {
            result.append(MessageFormat.format(messageFormat, e.getKey().getAmount(), e.getValue()));
        }
        return result.toString();
    }

    @Override
    public Iterator<Coin> iterator() {
        return coinMap.keySet().iterator();
    }
}
