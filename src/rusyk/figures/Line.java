package rusyk.figures;

import rusyk.UploadedFile;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class Line extends Shape {

    private int x1, y1, x2, y2;
    private boolean isArrow;
    private static final int SELECTION_INDENT = 3;

    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param isArrow
     */
    public Line(int x1, int y1, int x2, int y2, boolean isArrow) {

        this.isArrow = isArrow;

        if (Math.abs(x2-x1) > Math.abs(y2-y1)) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y1;
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1;
            this.y2 = y2;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        final int thickness = 2;
        //graphics2D.setStroke(new BasicStroke(thickness));

        final float dashness[] = {5.0f};
        final BasicStroke dashed =
                new BasicStroke(2,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dashness, 0.0f);

        if(selected){
            graphics2D.setStroke(dashed);
        } else {
            graphics2D.setStroke(new BasicStroke(thickness));
        }

        graphics2D.drawLine(x1, y1, x2, y2);

        if (isArrow) {

            Polygon arrow = new Polygon();
            int arrowHeight = 6;
            int arrowWidth  = 3;

            if(x2>x1) {
                //right arrow
                arrow.addPoint(x2, y1);
                arrow.addPoint(x2 - arrowHeight, y1 + arrowWidth);
                arrow.addPoint(x2 - arrowHeight, y1 - arrowWidth);
            } else if (x1>x2) {
                //left arrow
                arrow.addPoint(x2, y1);
                arrow.addPoint(x2 + arrowHeight, y1 + arrowWidth);
                arrow.addPoint(x2 + arrowHeight, y1 - arrowWidth);
            } else if(y2>y1) {
                //down arrow
                arrow.addPoint(x2, y2);
                arrow.addPoint(x2 - arrowWidth, y2 - arrowHeight);
                arrow.addPoint(x2 + arrowWidth, y2 - arrowHeight);
            } else if (y1>y2) {
                //up arrow
                arrow.addPoint(x1, y2);
                arrow.addPoint(x2 - arrowWidth, y2 + arrowHeight);
                arrow.addPoint(x2 + arrowWidth, y2 + arrowHeight);
            }

            graphics2D.draw(arrow);
            graphics2D.fillPolygon(arrow);
        }

        graphics2D.setStroke(oldStroke);
    }

    @Override
    public boolean hasPoint(int x, int y) {
        int x1 = Math.min(this.x1, this.x2);
        int x2 = Math.max(this.x1, this.x2);
        int y1 = Math.min(this.y1, this.y2);
        int y2 = Math.max(this.y1, this.y2);

        if (x1 == x2) {
            //vertical line
            return(x > x1-SELECTION_INDENT && x < x2+SELECTION_INDENT && y > y1 && y < y2);
        } else if (y1 == y2) {
            //horizontal line
            return(x > x1 && x < x2 && y > y1-SELECTION_INDENT && y < y2+SELECTION_INDENT);
        } else {
            return false;
        }
    }

    @Override
    public UploadedFile getChartFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getBlockNumber() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
