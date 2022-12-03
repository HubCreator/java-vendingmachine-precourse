package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.enums.Coin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinStatus implements Iterable<Coin> {

    private static final String message = "자판기가 보유한 동전\n";
    private static final String messageFormat = "{0}원 - {1}개\n";

    private final Map<Coin, Integer> coinMap;
    private static int amountTotal = 0;

    private CoinStatus(List<Coin> coins) {
        coinMap = new EnumMap<>(Coin.class);
        Coin[] values = Coin.values();
        for (Coin value : values) {
            coinMap.put(value, 0);
        }

        for (Coin coin : coins) {
            coinMap.put(coin, coinMap.get(coin) + 1);
        }
    }

    public static CoinStatus create(int amount) {
        List<Coin> result = new ArrayList<>();
        do {
            int randomIndex = Randoms.pickNumberInList(getEntry());
            Coin coin = Coin.getCoin(randomIndex);
            int currentCoinAmount = coin.getAmount();
            if (amount >= currentCoinAmount) {
                amountTotal += currentCoinAmount;
                result.add(coin);
                amount -= currentCoinAmount;
            }
        } while (amount > 0);

        return new CoinStatus(result);
    }

    private static List<Integer> getEntry() {
        return Arrays.asList(500, 100, 50, 10);
    }

    public String getStatus() {
        StringBuilder result = new StringBuilder(message);
        for (Map.Entry<Coin, Integer> e : coinMap.entrySet()) {
            result.append(MessageFormat.format(messageFormat, e.getKey().getAmount(), e.getValue()));
        }
        return result.toString();
    }

    public boolean isSameAmountTotal(int value) {
        return amountTotal == value;
    }

    @Override
    public Iterator<Coin> iterator() {
        return coinMap.keySet().iterator();
    }

    public String getBalance(int purchaseAmount) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Coin, Integer> e : coinMap.entrySet()) {
            if (purchaseAmount == 0) break;
            int count = 0;
            while (e.getKey().getAmount() <= purchaseAmount && e.getValue() > 0) {
                count++;
                purchaseAmount -= e.getKey().getAmount();
                coinMap.put(e.getKey(), e.getValue() - 1);
            }
            if (count > 0) {
                result.append(MessageFormat.format(messageFormat, e.getKey().getAmount(), count));
            }
        }

        return result.toString();
    }
}
