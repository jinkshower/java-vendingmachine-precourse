package vendingmachine;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.domain.RandomCoinGenerator;

class RandomCoinGeneratorTest {

    @DisplayName("무작위로 동전을 생성한다")
    @Test
    void createCoins() {
        RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator();
        System.out.println(randomCoinGenerator.generate(450));

    }

}
