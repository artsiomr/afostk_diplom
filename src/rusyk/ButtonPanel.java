package rusyk;

import rusyk.buttons.FigureButton;
import rusyk.buttons.LineButton;
import rusyk.buttons.RectangleButton;
import rusyk.event.DrawEvent;
import rusyk.event.FigureButtonPressEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public class ButtonPanel extends JPanel implements MouseListener, FigureButtonPressEvent.FigureButtonPressListener {

    public int x = 50;
    
    private List<FigureButton> figureButtons = new ArrayList<FigureButton>();

    public ButtonPanel() {

        JLabel fieldLabel = new JLabel("Создать объект");

        int buttonDimensionX = 95;
        int buttonDimensionY = 20;

        FigureButton rect41Btn = new RectangleButton("Блок 4x1", 4*x, x);
        rect41Btn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(rect41Btn);
        
        FigureButton rect21Btn = new RectangleButton("Блок 2x1", 2*x, x);
        rect21Btn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(rect21Btn);
        
        FigureButton rect11Btn = new RectangleButton("Блок 1x1", x, x);
        rect11Btn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(rect11Btn);
        
        FigureButton rect14Btn = new RectangleButton("Блок 1x4", x, 4*x);
        rect14Btn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(rect14Btn);
        
        FigureButton rect12Btn = new RectangleButton("Блок 1x2", x, 2*x);
        rect12Btn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(rect12Btn);        
        
        FigureButton arrowBtn = new LineButton("Стрелка", true);
        arrowBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(arrowBtn);

        FigureButton lineBtn = new LineButton("Линия", false);
        lineBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        figureButtons.add(lineBtn);

        add(fieldLabel);
        add(rect11Btn);
        add(rect21Btn);
        add(rect41Btn);
        add(rect12Btn);
        add(rect14Btn);
        add(arrowBtn);
        add(lineBtn);
        //add(controlPointBtn);

        for (FigureButton btn : figureButtons) {
            btn.addFigureButtonPressListener(this);
        }
    }


    public boolean isAnybodyPressed() {
        for (FigureButton btn : figureButtons) {
            if(btn.isPressed()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse is clicked on draw panel");
        for (FigureButton btn : figureButtons) {
            btn.mouseClicked(e);
        }
        if(!isAnybodyPressed()) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse is pressed on draw panel");
        for (FigureButton btn : figureButtons) {
            btn.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse is released on draw panel");
        for (FigureButton btn : figureButtons) {
            btn.mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void addDrawEventListener(DrawEvent.DrawEventListener listener) {
        for (FigureButton btn : figureButtons) {
            btn.addDrawEventListener(listener);
        }
    }

    @Override
    public void onFigureButtonPressEvent(FigureButtonPressEvent e) {
        for (FigureButton btn : figureButtons) {
            btn.onFigureButtonPressEvent(e);
        }
    }
}
