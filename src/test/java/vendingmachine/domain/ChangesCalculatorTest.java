package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static vendingmachine.domain.Coin.COIN_100;
import static vendingmachine.domain.Coin.COIN_50;
import static vendingmachine.domain.Coin.COIN_500;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChangesCalculatorTest {

    private static Map<Coin, Integer> makeMachine() {
        Map<Coin, Integer> map = new EnumMap<>(Coin.class);
        map.put(COIN_100, 4);
        map.put(COIN_50, 1);
        return map;
    }

    @DisplayName("남은 금액과 동전으로 잔돈을 반환 한다")
    @Test
    void calculateChanges() {
        ChangesCalculator changesCalculator = new ChangesCalculator(makeMachine(), 500);
        Map<Coin, Integer> actual = changesCalculator.execute();

        assertThat(actual).isEqualTo(makeMachine());
    }
}
