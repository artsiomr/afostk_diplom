package rusyk.figures;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class Line extends Shape {

    private int x1, y1, x2, y2;

    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param isArrow
     */
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        final int thickness = 3;
        graphics2D.setStroke(new BasicStroke(thickness));
        
        if (Math.abs(x2-x1) > Math.abs(y2-y1)) {
            graphics2D.drawLine(x1, y1, x2, y1);
            
                Stroke lineStroke = graphics2D.getStroke();
                graphics2D.setStroke(new BasicStroke(2));

                if(x2>x1) {
                    //right arrow
                    graphics2D.drawLine(x2+2, y1, x2-6, y1-6);
                    graphics2D.drawLine(x2+2, y1, x2-6, y1+6);
                } else if (x1>x2) {
                    //left arrow
                    graphics2D.drawLine(x2-2, y1, x2+6, y1-6);
                    graphics2D.drawLine(x2-2, y1, x2+6, y1+6);
                }            

                graphics2D.setStroke(lineStroke);
            //}
            
        } else {
            graphics2D.drawLine(x1, y1, x1, y2);
            
            //if(isArrow) {
            
                Stroke lineStroke = graphics2D.getStroke();
                graphics2D.setStroke(new BasicStroke(2));

                if(y2>y1) {
                    //down arrow
                    graphics2D.drawLine(x1, y2+2, x1-6, y2-6);
                    graphics2D.drawLine(x1, y2+2, x1+6, y2-6);
                } else if (y1>y2) {
                    //up arrow
                    graphics2D.drawLine(x1, y2-2, x1+6, y2+6);
                    graphics2D.drawLine(x1, y2-2, x1-6, y2+6);
                }            

                graphics2D.setStroke(lineStroke);
            //}
        }        
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

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
