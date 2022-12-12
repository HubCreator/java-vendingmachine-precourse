package vendingmachine.controller;

import vendingmachine.domain.Status;
import vendingmachine.dto.ReadInputCoinDto;
import vendingmachine.view.IOViewResolver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {

    private final IOViewResolver ioViewResolver;
    private final Map<Status, Supplier<Status>> statusMap;

    public Controller(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.statusMap = new EnumMap<>(Status.class);
        initStatusMap();
    }

    private void initStatusMap() {
        statusMap.put(Status.INPUT_COIN, this::inputCoin);
    }

    private Status inputCoin() {
        ReadInputCoinDto readInputCoinDto = ioViewResolver.inputViewResolve(ReadInputCoinDto.class);

        return null;
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
