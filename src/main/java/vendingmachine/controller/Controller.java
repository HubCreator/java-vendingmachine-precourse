package vendingmachine.controller;

import vendingmachine.view.IOViewResolver;

public class Controller {

    private final IOViewResolver ioViewResolver;

    public Controller(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }
}
