package rusyk.figures;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class Rectangle extends FileShape {

    protected int x, y;
    protected int width, height;
    protected static int count;
    private int index;


    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        count++;
        index = count;
        setNumber(String.valueOf(index));
        setName("Блок " + index);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();

        final int thickness = 2;
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

        graphics2D.drawRect(x, y, width, height);
        
        graphics2D.setFont(new Font( "Times New Roman", Font.BOLD, 12 ));

        graphics2D.drawString(getNumber(), this.x+width-10, this.y-5);

        drawString(graphics2D, getName(), this.x+5, this.y+15);

        //graphics2D.drawString(getName(), this.x+5, this.y+15);

        graphics2D.setStroke(oldStroke);

    }

    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    @Override
    public boolean hasPoint(int x, int y) {
        int x1 = this.x;
        int x2 = this.x + width;
        int y1 = this.y;
        int y2 = this.y + height;

        return(x > x1 && y > y1 && x < x2 && y < y2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
