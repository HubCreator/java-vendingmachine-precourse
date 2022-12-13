package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.items.Item;
import vendingmachine.domain.items.ItemStock;
import vendingmachine.domain.items.Items;
import vendingmachine.dto.output.PrintChangeDto;
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
    private final TreeMap<Coin, Integer> changeMap;
    private Items items;
    private Money inputMoney;

    public VendingMachine(int change) {
        this.change = new Money(change);
        this.changeMap = initChangeMap(change);
    }

    private TreeMap<Coin, Integer> initChangeMap(int amount) {
        TreeMap<Coin, Integer> result = new TreeMap<>();
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
        TreeMap<Item, ItemStock> map = new TreeMap<>();

        String[] items = itemsInfo.split(";");
        for (String item : items) {
            item = item.substring(1, item.length() - 1);
            String[] infos = item.split(",");
            map.put(new Item(infos[0], infos[1]), new ItemStock(infos[2]));
        }
        this.items = new Items(map);
    }

    public void setMoney(int amount) {
        this.inputMoney = new Money(amount);
    }

    public boolean haveEnoughMoney() {
        return items.haveEnoughMoney(inputMoney);
    }

    public boolean canPurchase(Item item) {
        return items.canPurchase(item, inputMoney);
    }

    public void purchase(Item targetItem, boolean canPurchase) {
        if (!canPurchase) {
            return;
        }
        Item purchasedItem = items.purchase(targetItem);
        inputMoney.decrease(purchasedItem.getItemPrice());
    }


    public PrintChangeDto getChangeMap() {
        TreeMap<Coin, Integer> result = new TreeMap<>();

        if (change.isLowerOrEqual(inputMoney)) {
            return new PrintChangeDto(changeMap);
        }
        while (!inputMoney.isZero()) {
            Coin coin = changeMap.firstKey();
            if (!inputMoney.isLowerOrEqual(coin)) {
                continue;
            }
            changeMap.put(coin, changeMap.get(coin) - 1);
            result.put(coin, result.getOrDefault(coin, 0) + 1);
            inputMoney.decrease(coin);
            if (changeMap.get(coin) == 0) {
                changeMap.remove(coin);
            }
        }
        return new PrintChangeDto(result);
    }
}
