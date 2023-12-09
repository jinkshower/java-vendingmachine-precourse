package vendingmachine.controller;

import java.util.List;
import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.HeldAmount;
import vendingmachine.domain.Product;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.Storage;
import vendingmachine.domain.UserAmount;
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
        UserAmount userAmount = ExceptionHandler.repeatUntilValid(this::handleUserAmount);
        outputView.printUserAmount(userAmount.getAmount());

        int price = ExceptionHandler.repeatUntilValid(this::handlePurchase);
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

    private UserAmount handleUserAmount() {
        int userInput = inputView.readUserAmount();
        return new UserAmount(userInput);
    }

    private int handlePurchase() {
        String input = inputView.readPurchaseProduct();
        if (!Storage.exist(input)) {
            throw new IllegalArgumentException("[ERROR] 없는 상품입니다.");
        }
        if (Storage.isSoldOut(input) && !Storage.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 모두 팔린 상품입니다.");
        }
        Storage.sell(input);
        return Storage.findPriceByName(input);
    }
}
