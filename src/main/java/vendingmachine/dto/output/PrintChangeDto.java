package vendingmachine.dto.output;

import vendingmachine.domain.Coin;

import java.util.TreeMap;

public final class PrintChangeDto {
    private final TreeMap<Coin, Integer> changeMap;

    public PrintChangeDto(final TreeMap<Coin, Integer> changeMap) {
        this.changeMap = changeMap;
    }

    public TreeMap<Coin, Integer> getChangeMap() {
        return changeMap;
    }
}
