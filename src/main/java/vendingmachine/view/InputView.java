package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public int readHeldAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        try {
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR]유효한 금액을 입력해주세요.");
        }
    }
}
