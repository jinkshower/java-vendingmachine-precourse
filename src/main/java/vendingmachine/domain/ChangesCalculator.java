package vendingmachine.domain;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class ChangesCalculator {

    private final Map<Coin, Integer> machineChanges;
    private int userAmount;

    public ChangesCalculator(int userAmount, Map<Coin, Integer> machineChanges) {
        this.userAmount = userAmount;
        this.machineChanges = machineChanges;
    }

    public Map<Coin, Integer> execute() {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);
        while (!isOver()) {
            Coin currentHighest = findHighestCoin(machineChanges);
            changes.put(currentHighest, changes.getOrDefault(currentHighest, 0) + 1);
            userAmount -= currentHighest.getAmount();
        }
        return changes;
    }

    private Coin findHighestCoin(Map<Coin, Integer> machineChanges) {
        return machineChanges.keySet().stream()
                .max(Comparator.comparing(Coin::getAmount))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 찾을 수 없습니다."));

    }

    private int findMinimumAmount() {
        return machineChanges.keySet().stream()
                .min(Comparator.comparing(Coin::getAmount))
                .map(Coin::getAmount)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 찾을 수 없습니다"));
    }

    private boolean isOver() {
        return userAmount < findMinimumAmount();
    }
}
