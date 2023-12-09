package vendingmachine.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {

    COIN_500(500, "500원"),
    COIN_100(100, "100원"),
    COIN_50(50, "50원"),
    COIN_10(10, "10원");

    private final int amount;
    private final String message;

    Coin(final int amount, final String message) {
        this.amount = amount;
        this.message = message;
    }

    // 추가 기능 구현
    public static Coin from(int amount) {
        return Arrays.stream(values())
                .filter(coin -> coin.amount == amount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR]유효한 금액이 아닙니다"));
    }

    public static List<Integer> amounts() {
        return Arrays.stream(values())
                .map(coin -> coin.amount)
                .collect(Collectors.toList());
    }

    public String getMessage(int count) {
        return message + " - " + count + "개";
    }
}
