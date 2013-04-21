/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rusyk.figures;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class ControlPoint extends Shape {

    private int x1, y1;

    public ControlPoint(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        final int thickness = 4;
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawOval(x1-2, y1-2, 4, 4);
        
        graphics2D.setFont(new Font( "SansSerif", Font.BOLD, 12 ));
        graphics2D.drawString("Точка", this.x1-15, this.y1-10);
        
        //graphics2D.drawLine(x1, y1, x2, y2);
        graphics2D.setStroke(oldStroke);
    }

    @Override
    public boolean hasPoint(int x, int y) {
        return false;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }
}

