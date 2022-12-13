package vendingmachine.util;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StandardRandomNumbersGenerator implements RandomNumbersGenerator {
    private final Coin[] coins;

    public StandardRandomNumbersGenerator(Coin[] coins) {
        this.coins = coins;
    }

    @Override
    public TreeMap<Coin, Integer> generate(int amount) {
        TreeMap<Coin, Integer> result = new TreeMap<>();

        do {
            Coin coin = getCoin(amount);
            result.put(coin, result.getOrDefault(coin, 0) + 1);
            amount -= coin.getAmount();
        } while (amount > 0);

        return result;
    }

    private Coin getCoin(int amount) {
        List<Integer> entry = Arrays.stream(coins)
                .map(Coin::getAmount)
                .filter(m -> m <= amount)
                .collect(Collectors.toList());

        return Coin.map(Randoms.pickNumberInList(entry));
    }
}
