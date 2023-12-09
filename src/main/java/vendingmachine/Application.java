package vendingmachine;

import vendingmachine.controller.MainController;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();

        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}
