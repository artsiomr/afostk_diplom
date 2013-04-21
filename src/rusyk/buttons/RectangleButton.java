package rusyk.buttons;

import rusyk.event.DrawEvent;
import rusyk.figures.Rectangle;

import java.awt.event.MouseEvent;

/**
 * @author Maksim Turchyn
 */
public class RectangleButton extends FigureButton {

    //public static int WIDTH = 156;
    //public static int HEIGHT = 77;
    
    private int width, height;

    //public RectangleButton(String text) {
    //    super(text);
    //}
    
    public RectangleButton(String text, int width, int height) {
        super(text);
        this.width = width;
        this.height = height;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPressed()) {
            fireDrawEvent(new DrawEvent(new Rectangle(e.getX(), e.getY(), width, height)));
        }
    }
}