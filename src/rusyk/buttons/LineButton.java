package rusyk.buttons;

import rusyk.event.DrawEvent;
import rusyk.figures.Line;

import java.awt.event.MouseEvent;

/**
 * @author Maksim Turchyn
 */
public class LineButton extends FigureButton {

    private int x1, y1;
    private boolean mousePressed;

    public LineButton(String text) {
        super(text);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isPressed()) {
            mousePressed = true;
            x1 = e.getX();
            y1 = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isPressed() && mousePressed) {
            fireDrawEvent(new DrawEvent(new Line(x1, y1, e.getX(), e.getY())));
        }
        mousePressed = false;
    }
}
