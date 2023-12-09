package vendingmachine.util;

import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {}

    public static <T> T repeatUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> void repeatUntilValid(Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
