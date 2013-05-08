package rusyk;

import rusyk.figures.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public class DrawPanel extends JPanel {


    public static final int GRID_STEP = 6;
    public static final int SCALE_INDENT = 40;
    
    private List<rusyk.figures.Shape> shapes = new ArrayList<rusyk.figures.Shape>();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);
        drawScale(g2d);
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
            for ( Shape shape : shapes ) {
                if ( shape.hasPoint(mouseEvent.getX(),mouseEvent.getY())) {
                    shape.select();
                } else {
                    shape.unselect();
                }
            }
            repaint();
        }
    }

    private void drawScale(Graphics2D g2d) {
        final int width = getWidth();
        final int height = getHeight();
        
        final int start_x = SCALE_INDENT;
        final int start_y = height - SCALE_INDENT;
        
        g2d.setColor(Color.black);
        g2d.setFont(new Font( "Times New Roman", Font.BOLD, 12 ));
        
        g2d.drawLine(SCALE_INDENT, SCALE_INDENT, SCALE_INDENT, height - SCALE_INDENT); // vertical left
        g2d.drawLine(width - SCALE_INDENT, SCALE_INDENT, width - SCALE_INDENT, start_y); // vertical right
        g2d.drawLine(SCALE_INDENT, SCALE_INDENT, width - SCALE_INDENT, SCALE_INDENT); // horizontal top
        g2d.drawLine(SCALE_INDENT, start_y, width - SCALE_INDENT, start_y); // horizontal bottom
        
        int scale = 0;
                
        for (int i = start_x - 3; i < width - SCALE_INDENT; i += 30) {
            
            String scale_string = Integer.toString(scale);
            g2d.drawString(scale_string, i, start_y + 20);
            //g2d.drawLine(i, (int) getAlignmentY(), i, (int)(getAlignmentY() + height - SCALE_INDENT));
            scale += 1;
        }        
        
        scale = 0;
        for (int i = start_y + 4; i > SCALE_INDENT; i -= 30) {
            
            String scale_string = Integer.toString(scale);
            g2d.drawString(scale_string, start_x - 20, i);
            //g2d.drawLine(i, (int) getAlignmentY(), i, (int)(getAlignmentY() + height - SCALE_INDENT));
            scale += 1;
        }

        //for (int i = 0; i < height - SCALE_INDENT; i += GRID_STEP) {
        //    g2d.drawLine((int) getAlignmentX() + SCALE_INDENT, i, (int)(getAlignmentX() + width), i);
        //}
        
    }

    private void drawGrid(Graphics2D g2d) {
        final int width = getWidth();
        final int height = getHeight();
        
        g2d.setColor(Color.yellow);
        
        for (int i = SCALE_INDENT; i < width-SCALE_INDENT; i += GRID_STEP) {
            g2d.drawLine(i, (int) getAlignmentY() + SCALE_INDENT, i, (int)(getAlignmentY() + height - SCALE_INDENT));
        }

        for (int i = height - SCALE_INDENT; i > SCALE_INDENT; i -= GRID_STEP) {
            g2d.drawLine((int) getAlignmentX() + SCALE_INDENT, i, (int)(getAlignmentX() + width - SCALE_INDENT), i);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);    //To change body of overridden methods use File | Settings | File Templates.
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public void draw(final rusyk.figures.Shape shape) {
        System.out.println("Drawing new shape");
        shapes.add(shape);
        repaint();
    }
}
