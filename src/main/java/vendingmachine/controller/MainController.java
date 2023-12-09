package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.HeldAmount;
import vendingmachine.domain.Product;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.Storage;
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

        ExceptionHandler.repeatUntilValid(this::handleStoreProduct);
        System.out.println(Storage.getStorage());
    }

    private HeldAmount handleHeldAmount() {
        int input = inputView.readHeldAmount();
        return new HeldAmount(input);
    }

    private void handleStoreProduct() {
        List<String[]> input = inputView.readProduct();
        for (String[] strings: input) {
            Product product = new Product(strings[0], Integer.parseInt(strings[1]));
            Storage.save(product, Integer.parseInt(strings[2]));
        }
    }
}
