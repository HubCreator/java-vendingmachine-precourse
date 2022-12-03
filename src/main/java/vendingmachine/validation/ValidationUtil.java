package vendingmachine.validation;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {
    public static List<Item> getItems(String input) {
        List<Item> result = new ArrayList<>();

        String[] split = input.split(";");
        for (String item : split) {
            item = item.substring(1, item.length() - 1);
            String[] information = item.split(",");
            result.add(new Item(information[0], Integer.parseInt(information[1]), Integer.parseInt(information[2])));
        }
        return result;
    }

    public static List<Coin> getCoin(int amount) {
        List<Coin> result = new ArrayList<>();
        do {
            int randomIndex = Randoms.pickNumberInRange(0, Coin.values().length - 1);
            Coin coin = Coin.getRandomCoin(randomIndex);
            if (amount >= coin.getAmount()) {
                result.add(coin);
                amount -= coin.getAmount();
            }
        } while (amount > 0);
        return result;
    }
}
