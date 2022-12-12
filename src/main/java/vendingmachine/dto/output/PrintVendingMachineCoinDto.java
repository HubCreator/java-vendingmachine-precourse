package vendingmachine.dto.output;

import vendingmachine.domain.Coin;

import java.util.Map;

public class PrintVendingMachineCoinDto {

    private final Map<Coin, Integer> coinMap;

    public PrintVendingMachineCoinDto(Map<Coin, Integer> coinMap) {
        this.coinMap = coinMap;
    }

    public Map<Coin, Integer> getCoinMap() {
        return coinMap;
    }
}
