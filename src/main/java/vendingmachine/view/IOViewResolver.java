package vendingmachine.view;

import vendingmachine.dto.input.ReadChangeDto;
import vendingmachine.dto.input.ReadItemNameDto;
import vendingmachine.dto.input.ReadItemsInfoDto;
import vendingmachine.dto.input.ReadMoneyDto;
import vendingmachine.dto.output.PrintChangeDto;
import vendingmachine.dto.output.PrintInputMoneyDto;
import vendingmachine.dto.output.PrintVendingMachineCoinDto;
import vendingmachine.view.exception.NotFoundViewException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IOViewResolver {
    private final Map<Class<?>, Supplier<Object>> inputViewMap = new HashMap<>();
    private final Map<Class<?>, Consumer<Object>> outputViewMap = new HashMap<>();

    public IOViewResolver(InputView inputView, OutputView outputView) {
        initInputViewMappings(inputView);
        initOutputViewMappings(outputView);
    }

    private void initInputViewMappings(InputView inputView) {
        inputViewMap.put(ReadChangeDto.class, inputView::readChange);
        inputViewMap.put(ReadItemsInfoDto.class, inputView::readItemsInfo);
        inputViewMap.put(ReadMoneyDto.class, inputView::readMoney);
        inputViewMap.put(ReadItemNameDto.class, inputView::readItemName);

    }

    private void initOutputViewMappings(OutputView outputView) {
        outputViewMap.put(PrintVendingMachineCoinDto.class, dto -> outputView.printVendingMachineCoin((PrintVendingMachineCoinDto) dto));
        outputViewMap.put(PrintInputMoneyDto.class, dto -> outputView.printInputMoney((PrintInputMoneyDto) dto));
        outputViewMap.put(PrintChangeDto.class, dto -> outputView.printChange((PrintChangeDto) dto));
//        outputViewMap.put(PrintExceptionDto.class, dto -> outputView.printException((PrintExceptionDto) dto));
    }

    public <T> T inputViewResolve(final Class<T> type) {
        try {
            return type.cast(inputViewMap.get(type).get());
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }

    public void outputViewResolve(final Object dto) {
        try {
            outputViewMap.get(dto.getClass()).accept(dto);
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }
}
