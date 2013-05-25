package rusyk.figures;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 25.05.13
 * Time: 16:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class NumberShape extends NamedShape {

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
