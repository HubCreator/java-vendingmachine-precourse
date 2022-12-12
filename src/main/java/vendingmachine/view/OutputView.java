package vendingmachine.view;

import vendingmachine.domain.Coin;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    private static class OutputViewSingletonHelper {

        private static final OutputView OUTPUT_VIEW = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewSingletonHelper.OUTPUT_VIEW;
    }

    public void printVendingMachineCoin(PrintVendingMachineCoinDto dto) {
        Map<Coin, Integer> coinMap = dto.getCoinMap();
        StringBuilder result = new StringBuilder("\n자판기가 보유한 동전\n");
        for (Coin value : Coin.values()) {
            if (coinMap.containsKey(value)) {
                result.append(String.format("%d원 - %d개\n", value.getAmount(), coinMap.get(value)));
                continue;
            }
            result.append(String.format("%d원 - %d개\n", value.getAmount(), 0));
        }
        System.out.println(result);
    }
}
