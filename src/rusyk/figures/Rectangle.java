package rusyk.figures;

import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class Rectangle extends Shape {

    private int x, y;
    private int width, height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();
        final int thickness = 3;
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(x, y, width, height);
        
        graphics2D.setFont(new Font( "SansSerif", Font.BOLD, 12 ));
        graphics2D.drawString("Блок N", this.x+5, this.y+20);
        
        graphics2D.setStroke(oldStroke);

    }

    @Override
    public boolean hasPoint(int x, int y) {
        return false;
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
