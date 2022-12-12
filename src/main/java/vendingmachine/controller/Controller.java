package vendingmachine.controller;

import vendingmachine.domain.Status;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.input.ReadChangeDto;
import vendingmachine.dto.input.ReadItemsInfoDto;
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
        statusMap.put(Status.INPUT_CHANGE, this::inputChange);
        statusMap.put(Status.INPUT_ITEMS_INFO, this::inputItemsInfo);
        statusMap.put(Status.INPUT_MONEY, this::inputMoney);
    }

    public Status run(Status status) {
        try {
            return statusMap.get(status).get();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return status;
        }
    }

    private Status inputChange() {
        ReadChangeDto readChangeDto = ioViewResolver.inputViewResolve(ReadChangeDto.class);
        vendingMachine = new VendingMachine(readChangeDto.getVendingMachineCoin());
        ioViewResolver.outputViewResolve(vendingMachine.printCoinStatus());
        return Status.INPUT_ITEMS_INFO;
    }

    private Status inputItemsInfo() {
        ReadItemsInfoDto readItemsInfoDto = ioViewResolver.inputViewResolve(ReadItemsInfoDto.class);
        vendingMachine.addItems(readItemsInfoDto.getItemsInfo());
        return Status.INPUT_MONEY;
    }

    private Status inputMoney() {

        return Status.INPUT_ITEM_NAME;
    }
}
