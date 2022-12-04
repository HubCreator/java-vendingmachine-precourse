package vendingmachine.utils;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.enums.Coin;

import java.util.List;

public class GenerateRandomCoin {

    public static Coin getRandomCoin(List<Integer> entry) {
        int randomIndex = Randoms.pickNumberInList(entry);
        return Coin.getCoin(randomIndex);
    }
}
