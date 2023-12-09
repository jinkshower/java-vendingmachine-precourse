package vendingmachine.domain;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static final Map<Product, Integer> storage = new HashMap<>();

    public static void save(Product product, int amount) {
        storage.put(product, amount);
    }

    public static void remove(Product product) {
        storage.replace(product, storage.get(product) - 1);
    }

    public static boolean isSoldOut(Product other) {
        return storage.keySet().stream()
                .filter(product -> product.isSame(other))
                .allMatch(product -> storage.get(product) <= 0);
    }

    public static boolean isEmpty() {
        return storage.keySet().stream()
                .allMatch(product -> storage.get(product) <= 0);
    }

    public static boolean exist(String name) {
        return storage.keySet().stream()
                .anyMatch(product -> product.hasSameName(name));
    }
}
