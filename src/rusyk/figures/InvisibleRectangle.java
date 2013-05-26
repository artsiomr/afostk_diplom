package rusyk.figures;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 25.05.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class InvisibleRectangle extends Rectangle {

    public InvisibleRectangle(int x, int y, int width, int height, String title) {
        super(x, y, width, height);
        setName(title);
        count--;
    }

    public void draw(Graphics2D graphics2D) {
        Stroke oldStroke = graphics2D.getStroke();

        final int thickness = 1;

        if(selected){
            //is selected set stroke visible
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        } else {
            //else transparent stroke
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }

        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(x, y, width, height);

        //set text visible
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        graphics2D.setFont(new Font( "Times New Roman", Font.BOLD, 20 ));
        graphics2D.drawString(getName(), this.x+20, this.y+15);
        graphics2D.setStroke(oldStroke);
    }

}
