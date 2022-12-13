package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.items.Item;
import vendingmachine.domain.items.Items;
import vendingmachine.dto.output.PrintInputMoneyDto;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        TreeMap<Item, Integer> map = new TreeMap<>();

        String[] items = itemsInfo.split(";");
        for (String item : items) {
            item = item.substring(1, item.length() - 1);
            String[] infos = item.split(",");
            Item key = new Item(infos[0], infos[1]);
            map.put(key, map.getOrDefault(key, 0) + Integer.parseInt(infos[2]));
        }
        this.items = new Items(map);
    }

    public void setMoney(int amount) {
        this.inputMoney = new Money(amount);
    }

    public boolean haveEnoughMoney(Item item) {
        if (!items.canBuySomething(item, inputMoney)) {
            return false;
        }
        return true;
    }

    public boolean canPurchase(Item item) {
        return items.canPurchase(item, inputMoney);
    }

    public boolean purchase(Item targetItem, boolean canPurchase) {
        if (!canPurchase) {
            return false;
        }
        items.purchase(targetItem);
        inputMoney.decrease(targetItem.getItemPrice());
        return true;
    }
}
