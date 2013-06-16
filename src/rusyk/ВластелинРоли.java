package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 09.06.13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public class ВластелинРоли implements ШинныйПодписчик {
    private boolean admin = false;

    private static ВластелинРоли instance = new ВластелинРоли();

    private ВластелинРоли() {
        СобытийнаяШина.подписатьсяНаСобытие("изменение.роли", this);
    }

    public static ВластелинРоли getInstance() {
        return instance;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public void оповестить(String имяСобытия, Object... аргументы) {
        admin = (Boolean) аргументы[0];
    }
}
