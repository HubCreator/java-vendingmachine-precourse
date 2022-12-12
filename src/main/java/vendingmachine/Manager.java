package vendingmachine;

import vendingmachine.controller.Controller;
import vendingmachine.domain.Status;
import vendingmachine.view.IOViewResolver;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public final class Manager {
    private static final Status INITIAL_STATUS = Status.INPUT_CHANGE;

    private Manager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        Controller controller = new Controller(ioViewResolver);
        Status status = INITIAL_STATUS;

        while (status != Status.EXIT) {
            status = controller.run(status);
        }
    }
}
