package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RandomCoinGenerator {

    public Map<Coin, Integer> generate(int machineAmount) {
        Map<Coin, Integer> machineChanges = new EnumMap<>(Coin.class);
        int randomPickedAmount = 0;

        while (machineAmount > 10) {
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

    private int pickRandom(List<Integer> coinAmounts) {
        return Randoms.pickNumberInList(coinAmounts);
    }
}
