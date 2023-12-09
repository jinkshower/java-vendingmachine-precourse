package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern PRODUCT_REGEX = Pattern.compile("^\\[[가-힣]+,\\d+,\\d+\\]$");
    private static final InputView instance = new InputView();

    public static InputView getInstance() {
        return instance;
    }

    private InputView() {
    }

    public int readHeldAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        try {
            String input = Console.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR]유효한 금액을 입력해주세요.");
        }
    }

    public List<String[]> readProduct() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String input = Console.readLine();
        String[] split = input.split(";");
        validateProduct(split);

        return parseInput(split);
    }

    private void validateProduct(String[] split) {
        for (String string: split) {
            if (!PRODUCT_REGEX.matcher(string).matches()) {
                throw new IllegalArgumentException("[ERROR]형식에 맞는 입력이 아닙니다.");
            }
        }
    }

    private List<String[]> parseInput(String[] input) {
        List<String[]> parsed = new ArrayList<>();
        for (String string : input) {
            String replaced= string.replace("[","").replace("]","");
            parsed.add(replaced.split(","));
        }
        return parsed;
    }
}
