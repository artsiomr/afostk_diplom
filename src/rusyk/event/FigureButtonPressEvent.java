package rusyk.event;

import rusyk.buttons.FigureButton;

/**
 * @author Maksim Turchyn
 */
public class FigureButtonPressEvent {

    private final FigureButton source;

    public FigureButtonPressEvent(FigureButton source) {
        this.source = source;
    }

    public FigureButton getSource() {
        return source;
    }

    public static interface FigureButtonPressListener {
        void onFigureButtonPressEvent(FigureButtonPressEvent e);
    }
}
