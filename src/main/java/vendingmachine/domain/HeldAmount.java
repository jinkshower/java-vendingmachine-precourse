package vendingmachine.domain;

public class HeldAmount {

    private final int amount;

    public HeldAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR]유효한 금액이 아닙니다.");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR]유효한 금액이 아닙니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
