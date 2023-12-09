package vendingmachine.controller;

import vendingmachine.domain.HeldAmount;
import vendingmachine.util.ExceptionHandler;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        HeldAmount heldAmount = ExceptionHandler.repeatUntilValid(this::handleHeldAmount);
        System.out.println(heldAmount.getAmount());
    }

    private HeldAmount handleHeldAmount() {
        int input = inputView.readHeldAmount();
        return new HeldAmount(input);
    }
}
