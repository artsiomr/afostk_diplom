package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.charts.ComplexChart;
import rusyk.figures.FileShape;
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
    public InvisibleRectangle title = new InvisibleRectangle(100, 50, 800, 20, "Название по умолчанию");
    private ShapeManager shapeManager = new ShapeManager();

    private List<rusyk.figures.Shape> shapes = new ArrayList<rusyk.figures.Shape>();

    public DrawPanel() {
        СобытийнаяШина.подписатьсяНаСобытие("перерисовать.фигуры", this);
        СобытийнаяШина.подписатьсяНаСобытие("удалить.фигуру", this);
        СобытийнаяШина.подписатьсяНаСобытие("сохранить.в.файл.фигуры", this);
        СобытийнаяШина.подписатьсяНаСобытие("загрузить.из.файла.фигуры", this);
        СобытийнаяШина.подписатьсяНаСобытие("about", this);
        СобытийнаяШина.подписатьсяНаСобытие("построить.параметрическую.зависимость", this);
        //СобытийнаяШина.подписатьсяНаСобытие("отобразить.параметрически", this);
        shapes.add(title);
    }

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
                    shape.unSelect();
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
            scale += 1;
        }

        scale = 0;
        for (int i = start_y + 4; i > SCALE_INDENT; i -= 30) {

            String scale_string = Integer.toString(scale);
            g2d.drawString(scale_string, start_x - 20, i);
            scale += 1;
        }
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
        } else if (имяСобытия.equals("загрузить.из.файла.фигуры")) {
            List<Shape> shapeList = shapeManager.load((File) аргументы[0]);
            shapes.clear();
            shapes.addAll(shapeList);
            repaint();
        } else if (имяСобытия.equals("сохранить.в.файл.фигуры"))  {
            shapeManager.save(shapes, (File)аргументы[0]);
        } else if (имяСобытия.equals("построить.параметрическую.зависимость"))  {

            Shape firstBlock = (Shape)аргументы[0];
            String secondBlockNumber = (String)аргументы[1];

            for (Shape shape : shapes) {

                if(shape.getBlockNumber().equals(secondBlockNumber)) {
                    //shape.getChartFile();
                    //System.out.println("Second block is " + shape.getBlockNumber());
                    //System.out.print(firstBlock.getChartFile().getStringFileContent());
                    //System.out.println("============================================");
                    //System.out.print(shape.getChartFile().getStringFileContent());

                    String otschetyY = firstBlock.getChartFile().getStringFileContent();
                    String otschetyX = shape.getChartFile().getStringFileContent();

                    String linesY[] = otschetyY.split("\\r?\\n");
                    String linesX[] = otschetyX.split("\\r?\\n");

                    String[][] values = new String[][]{
                        linesX,linesY
                    };

                    /*int i = 0;
                    while (i <= values.length-1) {
                        xyseries.add(Double.valueOf(linesX[i].trim()), Double.valueOf(linesY[i].trim()));
                        //System.out.println(Double.valueOf(linesX[i].trim()) +" -  "+  Double.valueOf(linesY[i].trim()));
                        i++;
                    }*/

                    String chartTitle = new String("Параметрическое представление ");

                    ComplexChart chart = new ComplexChart(chartTitle, values);
                }
            }

        } /*else if (имяСобытия.equals(("отобразить.параметрически")))  {

            for (Shape shape : shapes) {

                if(shape.hasChartFile() && shape.isSelected() != true) {
                    shape.setColor();
                } else {
                    shape.unsetColor();
                }
            }
            repaint();
        }  */
    }
}
