package vendingmachine.domain;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

public class ChangesCalculator {

    private final Map<Coin, Integer> machineChanges;
    private int userAmount;

    public ChangesCalculator(Map<Coin, Integer> machineChanges, int userAmount) {
        this.machineChanges = machineChanges;
        this.userAmount = userAmount;
    }

    public Map<Coin, Integer> execute() {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);
        removeZero();
        while (!isOver()) {
            Coin currentHighest = findHighestCoin();
            changes.put(currentHighest, changes.getOrDefault(currentHighest, 0) + 1);
            machineChanges.replace(currentHighest, machineChanges.get(currentHighest) - 1);
            removeZero();
            userAmount -= currentHighest.getAmount();
        }
        return changes;
    }

    private void removeZero() {
        for (Coin coin: machineChanges.keySet()) {
            if (machineChanges.get(coin) <= 0) {
                machineChanges.remove(coin);
            }
        }
    }

    private Coin findHighestCoin() {
        return machineChanges.keySet().stream()
                .max(Comparator.comparing(Coin::getAmount))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] rr찾을 수 없습니다."));

    }

    private int findMinimumAmount() {
        return machineChanges.keySet().stream()
                .min(Comparator.comparing(Coin::getAmount))
                .map(Coin::getAmount)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 찾을 수 없습니다"));
    }

    private boolean isOver() {
        if (machineChanges.isEmpty()) {
            return true;
        }
        return userAmount < findMinimumAmount();
    }
}
