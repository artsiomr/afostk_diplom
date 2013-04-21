package rusyk.figures;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public abstract class Shape {

    public abstract void draw(Graphics2D graphics2D);

    public abstract boolean hasPoint(int x, int y);

}
