package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {
    public static List<Item> getItems(String input) {
        List<Item> result = new ArrayList<>();

        String[] split = input.split(";");
        for (String item : split) {
            item = item.substring(1, item.length() - 1);
            String[] information = item.split(",");
            result.add(new Item(information[0], Integer.parseInt(information[1]), Integer.parseInt(information[2])));
        }
        return result;
    }
}
