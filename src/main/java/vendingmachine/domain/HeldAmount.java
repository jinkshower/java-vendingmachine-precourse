package vendingmachine.domain;

public class HeldAmount {

    private static final int MINIMUM_AMOUNT = 0;
    private static final int BASIC_UNIT = 10;

    private final int amount;

    public HeldAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("[ERROR]유효한 금액이 아닙니다.");
        }
        if (amount % BASIC_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR]유효한 금액이 아닙니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
