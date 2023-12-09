package vendingmachine.view;

import java.util.Map;
import java.util.StringJoiner;
import vendingmachine.domain.Coin;

public class OutputView {

    private static final OutputView instance = new OutputView();

    public static OutputView getInstance() {
        return instance;
    }

    private OutputView() {
    }

    public void printMachineCoins(Map<Coin, Integer> machineCoins) {
        System.out.println("자판기가 보유한 동전");
        String formattedCoins = formatCoin(machineCoins);
        System.out.println(formattedCoins);
    }

    public void printUserAmount(int amount) {
        System.out.printf("투입금액 : %d원%n", amount);
    }

    public void printChanges(Map<Coin, Integer> changes) {
        System.out.println("잔돈");
        String formattedCoins = formatCoin(changes);
        System.out.println(formattedCoins);
    }

    private String formatCoin(Map<Coin, Integer> machineCoins) {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Coin coin : machineCoins.keySet()) {
            stringJoiner.add(coin.getMessage(machineCoins.get(coin)));
        }
        return stringJoiner.toString();
    }
}
