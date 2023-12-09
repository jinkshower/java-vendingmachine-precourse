package vendingmachine.domain;

import java.util.Objects;

public class Product {

    private final String name;
    private final int price;

    public Product(String name, int price) {
        validate(price);
        this.name = name;
        this.price = price;
    }

    private void validate(int price) {
        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR]금액은 10단위로 나누어져야 합니다.");
        }
    }

    public boolean hasSameName(String name) {
        return this.name.equals(name);
    }

    public boolean isSame(Product other) {
        return this.name.equals(other.name);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
