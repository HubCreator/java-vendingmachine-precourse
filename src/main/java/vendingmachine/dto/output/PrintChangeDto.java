package vendingmachine.dto.output;

import vendingmachine.domain.Coin;

import java.util.TreeMap;

public class PrintChangeDto {
    private final TreeMap<Coin, Integer> changeMap;

    public PrintChangeDto(TreeMap<Coin, Integer> changeMap) {
        this.changeMap = changeMap;
    }

    public TreeMap<Coin, Integer> getChangeMap() {
        return changeMap;
    }
}
