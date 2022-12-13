package vendingmachine.domain;

import vendingmachine.domain.items.Item;
import vendingmachine.domain.items.ItemStock;
import vendingmachine.domain.items.Items;
import vendingmachine.dto.output.PrintChangeDto;
import vendingmachine.dto.output.PrintInputMoneyDto;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;
import vendingmachine.util.RandomNumbersGenerator;

import java.util.TreeMap;

public class VendingMachine {

    private final Money change;
    private final TreeMap<Coin, Integer> changeMap;
    private Items items;
    private Money inputMoney;

    public VendingMachine(int change, RandomNumbersGenerator generator) {
        this.change = new Money(change);
        this.changeMap = generator.generate(change);
    }

    public PrintVendingMachineCoinDto printCoinStatus() {
        return new PrintVendingMachineCoinDto(changeMap);
    }

    public PrintInputMoneyDto printInputMoney() {
        return new PrintInputMoneyDto(inputMoney);
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
}
