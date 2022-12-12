package vendingmachine.controller;

import vendingmachine.domain.Status;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.input.ReadInputCoinDto;
import vendingmachine.view.IOViewResolver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {

    private final IOViewResolver ioViewResolver;
    private final Map<Status, Supplier<Status>> statusMap;

    private VendingMachine vendingMachine;

    public Controller(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.statusMap = new EnumMap<>(Status.class);
        initStatusMap();
    }

    private void initStatusMap() {
        statusMap.put(Status.INPUT_COIN, this::inputCoin);
        statusMap.put(Status.INPUT_ITEMS_INFO, this::inputItemsInfo);
    }

    private Status inputItemsInfo() {
        return null;
    }

    private Status inputCoin() {
        ReadInputCoinDto readInputCoinDto = ioViewResolver.inputViewResolve(ReadInputCoinDto.class);
        vendingMachine = new VendingMachine(readInputCoinDto.getVendingMachineCoin());
        ioViewResolver.outputViewResolve(vendingMachine.printCoinStatus());
        return Status.INPUT_ITEMS_INFO;
    }

    public Status run(Status status) {
        try {
            return statusMap.get(status).get();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return status;
        }
    }
}
