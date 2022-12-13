package vendingmachine.view;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;
import vendingmachine.dto.output.PrintChangeDto;
import vendingmachine.dto.output.PrintInputMoneyDto;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;

import java.util.Map;
import java.util.TreeMap;

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

    public void printInputMoney(PrintInputMoneyDto dto) {
        Money inputMoney = dto.getInputMoney();
        System.out.println(String.format("\n투입금액 : %d원", inputMoney.getMoney()));
    }

    public void printChange(PrintChangeDto dto) {
        TreeMap<Coin, Integer> changeMap = dto.getChangeMap();
        StringBuilder result = new StringBuilder("잔돈\n");
        for (Map.Entry<Coin, Integer> entry : changeMap.entrySet()) {
            result.append(String.format("%d원 - %d개\n", entry.getKey().getAmount(), entry.getValue()));
        }
        System.out.println(result);
    }
}
