package rusyk.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maksim Turchyn
 */
public class СобытийнаяШина {

    private Map<String, List<ШинныйПодписчик>> картаПодписчиков = new HashMap<String, List<ШинныйПодписчик>>();

    public void опубликоватьСобытие(final String имяСобытия, Object... аргументы) {
        if (!картаПодписчиков.containsKey(имяСобытия)) return;

        for (ШинныйПодписчик подписчик : картаПодписчиков.get(имяСобытия)) {
            подписчик.оповестить(аргументы);
        }
    }

    public void подписатьсяНаСобытие(final String имяСобытия, ШинныйПодписчик подписчик) {
        if (!картаПодписчиков.containsKey(имяСобытия)) {
            картаПодписчиков.put(имяСобытия, new ArrayList<ШинныйПодписчик>());
        }

        final List<ШинныйПодписчик> подписчикиСобытия = картаПодписчиков.get(имяСобытия);
        подписчикиСобытия.add(подписчик);
    }
}
