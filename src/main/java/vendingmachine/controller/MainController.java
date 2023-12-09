package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.HeldAmount;
import vendingmachine.domain.RandomCoinGenerator;
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
        RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator();
        Map<Coin, Integer> machineChanges = randomCoinGenerator.generate(heldAmount.getAmount());
        outputView.printMachineCoins(machineChanges);
    }

    private HeldAmount handleHeldAmount() {
        int input = inputView.readHeldAmount();
        return new HeldAmount(input);
    }
}
