package vendingmachine;

import vendingmachine.controller.Controller;
import vendingmachine.view.IOViewResolver;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class Manager {
    private Manager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        Controller controller = new Controller(ioViewResolver);

    }
}
