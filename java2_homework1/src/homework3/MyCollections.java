package homework3;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class MyCollections {
    public static void main(String[] args) {
        String[] arr = new String[]{"Автомобиль", "Двигатель", "КПП", "АКПП", "Трансмиссия", "Тормоза", "Двигатель", "Руль",
                "Шестерня", "Сальник", "КПП", "Синхранизатор", "Поршень", "Шатун", "Колодки", "Тормоза",
                "Клапан", "Прокладка", "КПП", "Радиатор"};

        HashMap<String, Integer> hm = new LinkedHashMap<>();
        for(String word: arr) {
            Integer res = hm.get(word);
            hm.put(word, res == null ? 1 : res + 1);
        }
        System.out.println(hm);
    }
}
