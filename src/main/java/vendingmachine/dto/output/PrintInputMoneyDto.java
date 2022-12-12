package vendingmachine.dto.output;

import vendingmachine.domain.Money;

public class PrintInputMoneyDto {
    private final Money inputMoney;

    public PrintInputMoneyDto(Money inputMoney) {
        this.inputMoney = inputMoney;
    }

    public Money getInputMoney() {
        return inputMoney;
    }
}
