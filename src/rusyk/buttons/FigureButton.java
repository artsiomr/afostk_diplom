package rusyk.buttons;

import rusyk.event.DrawEvent;
import rusyk.event.FigureButtonPressEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public abstract class FigureButton extends JButton implements MouseListener, FigureButtonPressEvent.FigureButtonPressListener {

    private boolean pressed;
    private List<DrawEvent.DrawEventListener> drawEventListeners = new ArrayList<DrawEvent.DrawEventListener>();
    private List<FigureButtonPressEvent.FigureButtonPressListener> figureButtonPressListeners =
            new ArrayList<FigureButtonPressEvent.FigureButtonPressListener>();

    // Constructors. -------------------------------------------------------------------------

    public FigureButton(String text) {
        super(text);
        addMouseClickProcess();
    }

    private void addMouseClickProcess() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                pressed = !pressed;
                System.out.println("Button is " + (pressed ? "pressed" : " not pressed"));
                if (pressed) {
                    fireButtonPressEvent();
                }
            }
        });
    }


    // Logic. -------------------------------------------------------------------------

    protected void fireDrawEvent(DrawEvent drawEvent) {
        System.out.println("Firing draw event");
        for (DrawEvent.DrawEventListener listener : drawEventListeners) {
            listener.onDrawEvent(drawEvent);
        }
    }

    protected void fireButtonPressEvent() {
        for (FigureButtonPressEvent.FigureButtonPressListener listener : figureButtonPressListeners) {
            listener.onFigureButtonPressEvent(new FigureButtonPressEvent(this));
        }
    }

    public boolean isPressed() {
        return pressed;
    }

    public void addDrawEventListener(DrawEvent.DrawEventListener listener) {
        drawEventListeners.add(listener);
    }

    public void addFigureButtonPressListener(FigureButtonPressEvent.FigureButtonPressListener listener) {
        figureButtonPressListeners.add(listener);
    }

    // Mouse listener implementation for draw panel. -------------------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void onFigureButtonPressEvent(FigureButtonPressEvent e) {
        if (e.getSource() != this) {
            pressed = false;
        }
    }
}
