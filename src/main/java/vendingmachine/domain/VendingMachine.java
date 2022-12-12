package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.items.Item;
import vendingmachine.domain.items.Items;
import vendingmachine.dto.output.PrintInputMoneyDto;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachine {

    private final Money change;
    private final Map<Coin, Integer> changeMap;
    private Items items;
    private Money inputMoney;

    public VendingMachine(int change) {
        this.change = new Money(change);
        this.changeMap = getChangeMap(change);
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

    public PrintInputMoneyDto printInputMoney() {
        return new PrintInputMoneyDto(inputMoney);
    }

    public void setItems(String itemsInfo) {
        List<Item> result = new ArrayList<>();
        String[] items = itemsInfo.split(";");
        for (String item : items) {
            item = item.substring(1, item.length() - 1);
            String[] infos = item.split(",");
            result.add(new Item(infos[0], infos[1], infos[2]));
        }
        this.items = new Items(result);
    }

    public void setMoney(int amount) {
        this.inputMoney = new Money(amount);
    }

    public void purchase(Item item) {
        Item result = items.canPurchase(item, inputMoney);
        result.purchase();
        inputMoney.decrease(result.getItemPrice());
    }
}
