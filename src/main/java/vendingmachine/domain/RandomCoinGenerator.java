package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RandomCoinGenerator {

    private static final int MINIMUM_COIN_AMOUNT = 10;

    public Map<Coin, Integer> generate(int machineAmount) {
        Map<Coin, Integer> machineChanges = initialize();
        int randomPickedAmount = 0;

        while (machineAmount > MINIMUM_COIN_AMOUNT) {
            randomPickedAmount = pickRandom(Coin.amounts());
            while (randomPickedAmount > machineAmount) {
                randomPickedAmount = pickRandom(Coin.amounts());
            }
            Coin coin = Coin.from(randomPickedAmount);
            machineChanges.put(coin, machineChanges.getOrDefault(coin, 0) + 1);
            machineAmount -= randomPickedAmount;
        }
        return machineChanges;
    }

    private Map<Coin, Integer> initialize() {
        Map<Coin, Integer> map = new EnumMap<>(Coin.class);
        for (Coin coin: Coin.values()) {
            map.put(coin, 0);
        }
        return map;
    }

    private int pickRandom(List<Integer> coinAmounts) {
        return Randoms.pickNumberInList(coinAmounts);
    }
}
