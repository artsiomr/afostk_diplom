package rusyk.figures;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 08.05.13
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */
public abstract class NamedShape extends Shape {

    private String number;
    private String name;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
