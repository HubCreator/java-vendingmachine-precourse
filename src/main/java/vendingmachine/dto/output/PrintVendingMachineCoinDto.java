package vendingmachine.dto.output;

import vendingmachine.domain.Coin;

import java.util.Map;

public final class PrintVendingMachineCoinDto {

    private final Map<Coin, Integer> coinMap;

    public PrintVendingMachineCoinDto(final Map<Coin, Integer> coinMap) {
        this.coinMap = coinMap;
    }

    public Map<Coin, Integer> getCoinMap() {
        return coinMap;
    }
}
