package rusyk.buttons;

import rusyk.event.DrawEvent;
import rusyk.figures.ControlPoint;

import java.awt.event.MouseEvent;

/**
 *
 * @author user
 */
public class ControlPointButton extends FigureButton {
    
    public ControlPointButton(String text) {
        super(text);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (isPressed()) {
            fireDrawEvent(new DrawEvent(new ControlPoint(e.getX(), e.getY())));
        }
    }
    
}
