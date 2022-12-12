package vendingmachine.view;

public class IOViewResolver {
    private final InputView inputView;
    private final OutputView outputView;

    public IOViewResolver(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }
}
