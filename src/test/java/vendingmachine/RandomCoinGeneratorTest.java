package vendingmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomCoinGeneratorTest {

    @DisplayName("무작위로 동전을 생성한다")
    @Test
    void createCoins() {
        RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator();
        System.out.println(randomCoinGenerator.generate(450));

    }

}
