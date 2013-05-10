package rusyk.figures;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Maksim Turchyn
 */
public abstract class Shape implements Serializable {

    protected boolean selected;

    public abstract void draw(Graphics2D graphics2D);

    public abstract boolean hasPoint(int x, int y);

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

}
