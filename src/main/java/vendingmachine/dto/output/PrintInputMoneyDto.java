package vendingmachine.dto.output;

import vendingmachine.domain.Money;

public final class PrintInputMoneyDto {
    private final Money inputMoney;

    public PrintInputMoneyDto(final Money inputMoney) {
        this.inputMoney = inputMoney;
    }

    public Money getInputMoney() {
        return inputMoney;
    }
}
