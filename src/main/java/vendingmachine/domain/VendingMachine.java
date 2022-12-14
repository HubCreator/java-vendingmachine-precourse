package vendingmachine.domain;

import vendingmachine.domain.items.Item;
import vendingmachine.domain.items.ItemStock;
import vendingmachine.domain.items.Items;
import vendingmachine.util.RandomNumbersGenerator;

import java.util.Map;
import java.util.TreeMap;

public final class VendingMachine {

    public static final String ITEMS_DELIMITER = ";";
    public static final String ITEM_INFOS_DELIMITER = ",";

    private final Money change;
    private final TreeMap<Coin, Integer> changeMap;
    private Items items;
    private Money inputMoney;

    public VendingMachine(int change, RandomNumbersGenerator generator) {
        this.change = new Money(change);
        this.changeMap = generator.generate(change);
    }

    public TreeMap<Coin, Integer> printCoinStatus() {
        return changeMap;
    }

    public Money printInputMoney() {
        return inputMoney;
    }

    public TreeMap<Coin, Integer> getChangeMap() {
        TreeMap<Coin, Integer> result = new TreeMap<>();
        TreeMap<Coin, Integer> tmp = new TreeMap<>();

        if (change.isLowerOrEqual(inputMoney)) {
            return changeMap;
        }
        getResult(result, tmp);
        return result;
    }

    private void getResult(TreeMap<Coin, Integer> result, TreeMap<Coin, Integer> tmp) {
        while (!inputMoney.isZero()) {
            Coin coin = changeMap.firstKey();
            if (needToSkip(tmp, coin)) {
                continue;
            }
            getResult(result, tmp, coin);
        }
    }

    private void getResult(TreeMap<Coin, Integer> result, TreeMap<Coin, Integer> tmp, Coin coin) {
        changeMap.put(coin, changeMap.get(coin) - 1);
        result.put(coin, result.getOrDefault(coin, 0) + 1);
        inputMoney.decrease(coin);
        if (changeMap.get(coin) == 0) {
            changeMap.remove(coin);
        }
        if (!tmp.isEmpty()) {
            result.putAll(tmp);
        }
    }

    private boolean needToSkip(TreeMap<Coin, Integer> tmp, Coin coin) {
        if (inputMoney.isLowerThan(coin)) {
            Map.Entry<Coin, Integer> poll = changeMap.pollFirstEntry();
            tmp.put(changeMap.pollFirstEntry().getKey(), poll.getValue());
            return true;
        }
        return false;
    }

    public void setItems(String itemsInfo) {
        TreeMap<Item, ItemStock> map = new TreeMap<>();

        String[] items = itemsInfo.split(ITEMS_DELIMITER);
        for (String item : items) {
            item = item.substring(1, item.length() - 1);
            String[] infos = item.split(ITEM_INFOS_DELIMITER);
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
}
