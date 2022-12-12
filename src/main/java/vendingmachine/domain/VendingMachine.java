package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachine {
    private final int coin;
    private final Map<Coin, Integer> coinMap;

    public VendingMachine(int coin) {
        this.coin = coin;
        this.coinMap = getCoinMap(coin);
    }

    private Map<Coin, Integer> getCoinMap(int amount) {
        Map<Coin, Integer> coinMap = new EnumMap<>(Coin.class);
        do {
            Coin randomCoin = getRandomCoinWithLimit(amount);
            coinMap.put(randomCoin, coinMap.getOrDefault(randomCoin, 0) + 1);
            amount -= randomCoin.getAmount();
        } while (amount > 0);
        return coinMap;
    }

    private Coin getRandomCoinWithLimit(int amount) {
        List<Integer> entry = Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .filter(m -> m <= amount)
                .collect(Collectors.toList());

        return Coin.map(Randoms.pickNumberInList(entry));
    }

    public PrintVendingMachineCoinDto printCoinStatus() {
        return new PrintVendingMachineCoinDto(coinMap);
    }
}
