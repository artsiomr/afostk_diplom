package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.figures.Rectangle;
import rusyk.figures.Shape;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 08.05.13
 * Time: 9:37
 * To change this template use File | Settings | File Templates.
 */
public class ActionPanel extends JPanel implements ШинныйПодписчик {

    JTextField nameField;
    JButton saveBtn;
    Rectangle rectangle;

    public ActionPanel() {
        СобытийнаяШина.подписатьсяНаСобытие("shape selection", this);

        nameField = new JTextField();
        nameField.setColumns(10);
        add(nameField);

        saveBtn = new JButton("Сохранить") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        rectangle.setName(nameField.getText());
                    }
                    СобытийнаяШина.опубликоватьСобытие("перерисовать.фигуры");
                }
            }
        };
        add(saveBtn);
    }

    @Override
    public void оповестить(String eventName, Object... аргументы) {
        Shape shape = (Shape) аргументы[0];
        if (shape instanceof Rectangle) {
            rectangle = (Rectangle) shape;
            nameField.setText(rectangle.getName());
        }

    }
}
