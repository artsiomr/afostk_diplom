package rusyk.event;

import rusyk.figures.Shape;

/**
 * @author Maksim Turchyn
 */
public class DrawEvent {

    public static interface DrawEventListener {
        void onDrawEvent(DrawEvent drawEvent);
    }

    private Shape shape;

    public DrawEvent(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }
}
