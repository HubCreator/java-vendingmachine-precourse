package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachine {
    private static final int MIN_UNIT = 10;

    private final int change;
    private final Map<Coin, Integer> changeMap;
    private Items items;

    public VendingMachine(int change) {
        this.change = validate(change);
        this.changeMap = getChangeMap(change);
    }

    private int validate(int change) {
        if (change % MIN_UNIT != 0) {
            throw new IllegalArgumentException("10 단위로 입력하셔야 합니다.");
        }
        return change;
    }

    private Map<Coin, Integer> getChangeMap(int amount) {
        Map<Coin, Integer> result = new EnumMap<>(Coin.class);
        do {
            Coin randomCoin = getRandomCoinWithLimit(amount);
            result.put(randomCoin, result.getOrDefault(randomCoin, 0) + 1);
            amount -= randomCoin.getAmount();
        } while (amount > 0);
        return result;
    }

    private Coin getRandomCoinWithLimit(int amount) {
        List<Integer> entry = Arrays.stream(Coin.values())
                .map(Coin::getAmount)
                .filter(m -> m <= amount)
                .collect(Collectors.toList());

        return Coin.map(Randoms.pickNumberInList(entry));
    }

    public PrintVendingMachineCoinDto printCoinStatus() {
        return new PrintVendingMachineCoinDto(changeMap);
    }

    public void addItems(String itemsInfo) {
        List<Item> result = new ArrayList<>();
        String[] items = itemsInfo.split(";");
        for (String item : items) {
            String[] infos = item.split(",");
            result.add(new Item(infos[0], infos[1], infos[2]));
        }
        this.items = new Items(result);
    }
}
