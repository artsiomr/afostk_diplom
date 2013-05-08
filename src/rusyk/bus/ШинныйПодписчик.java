package rusyk.bus;

/**
 * @author Maksim Turchyn
 */
public interface ШинныйПодписчик {

    void оповестить(String имяСобытия, Object... аргументы);
}
