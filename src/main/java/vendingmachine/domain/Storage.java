package vendingmachine.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static final Map<Product, Integer> storage = new HashMap<>();

    public static void save(Product product, int amount) {
        storage.put(product, amount);
    }

    public static void sell(String name) {
        Product found = findByName(name);
        storage.replace(found, storage.get(found) - 1);
    }

    public static Product findByName(String name) {
        return storage.keySet().stream()
                .filter(product -> product.hasSameName(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 없는 상품입니다."));
    }

    public static int findPriceByName(String name) {
        return findByName(name).getPrice();
    }

    public static int findMinimumPrice() {
        return storage.keySet().stream()
                .min(Comparator.comparing(Product::getPrice))
                .map(Product::getPrice)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 에러"));
    }

    public static boolean isSoldOut(String name) {
        return storage.keySet().stream()
                .filter(product -> product.hasSameName(name))
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

    public static Map<Product, Integer> getStorage() {
        return storage;
    }
}
