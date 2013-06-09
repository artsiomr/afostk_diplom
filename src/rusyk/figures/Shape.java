package rusyk.figures;

import rusyk.UploadedFile;

import java.awt.*;
import java.io.Serializable;

/**
 * @author Maksim Turchyn
 */
public abstract class Shape implements Serializable {

    protected boolean selected;

    protected boolean color;
    public abstract void draw(Graphics2D graphics2D);

    public abstract boolean hasPoint(int x, int y);

    public abstract UploadedFile getChartFile();
    public abstract String getBlockNumber();

    public boolean isSelected() {
        return selected;
    }

    public void setColor() {
        color = true;
    }

    public void unsetColor() {
        color = false;
    }

    public void select() {
        selected = true;
    }

    public void unSelect() {
        selected = false;
    }


}
