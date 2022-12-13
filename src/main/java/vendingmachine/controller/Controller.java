package vendingmachine.controller;

import vendingmachine.domain.Status;
import vendingmachine.domain.VendingMachine;
import vendingmachine.domain.items.Item;
import vendingmachine.dto.input.ReadChangeDto;
import vendingmachine.dto.input.ReadItemNameDto;
import vendingmachine.dto.input.ReadItemsInfoDto;
import vendingmachine.dto.input.ReadMoneyDto;
import vendingmachine.dto.output.PrintChangeDto;
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
        statusMap.put(Status.INPUT_ITEM_NAME, this::inputItemName);
        statusMap.put(Status.RETURN_CHANGE, this::returnChange);
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
        vendingMachine.setItems(readItemsInfoDto.getItemsInfo());
        return Status.INPUT_MONEY;
    }

    private Status inputMoney() {
        ReadMoneyDto readMoneyDto = ioViewResolver.inputViewResolve(ReadMoneyDto.class);
        vendingMachine.setMoney(readMoneyDto.getMoney());
        return Status.INPUT_ITEM_NAME;
    }

    private Status inputItemName() {
        ioViewResolver.outputViewResolve(vendingMachine.printInputMoney());
        if (!vendingMachine.haveEnoughMoney()) {
            return Status.RETURN_CHANGE;
        }
        ReadItemNameDto readItemNameDto = ioViewResolver.inputViewResolve(ReadItemNameDto.class);
        Item targetItem = new Item(readItemNameDto.getItemName());
        boolean canPurchase = vendingMachine.canPurchase(targetItem); // 재고와 해당 상품을 살 수 있는지 확인
        vendingMachine.purchase(targetItem, canPurchase);
        return Status.INPUT_ITEM_NAME;
    }

    private Status returnChange() {
        ioViewResolver.outputViewResolve(vendingMachine.getChangeMap());
        return Status.EXIT;
    }
}
