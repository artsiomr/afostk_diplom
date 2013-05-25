package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.figures.InvisibleRectangle;
import rusyk.figures.Shape;
import rusyk.io.ShapeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public class DrawPanel extends JPanel implements ШинныйПодписчик {


    public static final int GRID_STEP = 6;
    public static final int SCALE_INDENT = 40;
    public InvisibleRectangle title = new InvisibleRectangle(100, 50, 800, 70, "TESTSTST");
    private ShapeManager shapeManager = new ShapeManager();

    private List<rusyk.figures.Shape> shapes = new ArrayList<rusyk.figures.Shape>();

    public DrawPanel() {
        СобытийнаяШина.подписатьсяНаСобытие("перерисовать.фигуры", this);
        СобытийнаяШина.подписатьсяНаСобытие("удалить.фигуру", this);
        СобытийнаяШина.подписатьсяНаСобытие("сохранить.фигуры", this);
        СобытийнаяШина.подписатьсяНаСобытие("загрузить.фигуры", this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGrid(g2d);
        drawScale(g2d);
        title.draw(g2d);
        shapes.add(title);
        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        // super.processMouseEvent(mouseEvent);

        boolean smthWasSelected = false;

        if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
            for (Shape shape : shapes) {

                if (shape.hasPoint(mouseEvent.getX(), mouseEvent.getY())) {
                    if (!smthWasSelected) {
                        shape.select();
                        smthWasSelected = true;
                        СобытийнаяШина.опубликоватьСобытие("shape selection", shape);
                    }
                } else {
                    shape.unselect();
                }


            }
        }
        if (!smthWasSelected) {
            super.processMouseEvent(mouseEvent);
        }

        repaint();
    }

    private void drawScale(Graphics2D g2d) {
        final int width = getWidth();
        final int height = getHeight();

        final int start_x = SCALE_INDENT;
        final int start_y = height - SCALE_INDENT;

        g2d.setColor(Color.black);
        g2d.setFont(new Font("Times New Roman", Font.BOLD, 12));

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

        for (int i = SCALE_INDENT; i < width - SCALE_INDENT; i += GRID_STEP) {
            g2d.drawLine(i, (int) getAlignmentY() + SCALE_INDENT, i, (int) (getAlignmentY() + height - SCALE_INDENT));
        }

        for (int i = height - SCALE_INDENT; i > SCALE_INDENT; i -= GRID_STEP) {
            g2d.drawLine((int) getAlignmentX() + SCALE_INDENT, i, (int) (getAlignmentX() + width - SCALE_INDENT), i);
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

    public void delete(final Shape shape) {
        System.out.println("Removing the shape");
        shapes.remove(shape);
        repaint();
    }

    @Override
    public void оповестить(String имяСобытия, Object... аргументы) {
        if (имяСобытия.equals("перерисовать.фигуры")) {
            this.repaint();
        } else if (имяСобытия.equals("удалить.фигуру")) {
            delete((Shape)аргументы[0]);
        } else if (имяСобытия.equals("загрузить.фигуры")) {
            List<Shape> shapeList = shapeManager.load((File) аргументы[0]);
            shapes.clear();
            shapes.addAll(shapeList);
            repaint();
        } else if (имяСобытия.equals(("сохранить.фигуры")))  {
            shapeManager.save(shapes, (File)аргументы[0]);
        }
    }
}
