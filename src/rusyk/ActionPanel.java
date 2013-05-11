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


    // поле "Номер блока"
    JLabel numberLabel;
    JTextField numberField;

    // поле "Название блока"
    JLabel nameLabel;
    JTextField nameField;

    JButton saveBtn;
    JButton deleteBtn;
    Rectangle rectangle;

    public ActionPanel() {
        СобытийнаяШина.подписатьсяНаСобытие("shape selection", this);


        // поле "Номер блока"
        numberLabel = new JLabel("Номер блока: ");
        numberField = new JTextField();
        numberField.setColumns(10);
        add(numberLabel);
        add(numberField);

        // поле "Название блока"
        nameLabel = new JLabel("Название блока: ");
        nameField = new JTextField();
        nameField.setColumns(10);
        add(nameLabel);
        add(nameField);


        saveBtn = new JButton("Сохранить") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        rectangle.setName(nameField.getText());
                        rectangle.setNumber(numberField.getText());
                    }
                    СобытийнаяШина.опубликоватьСобытие("перерисовать.фигуры");
                }
            }
        };
        add(saveBtn);

        deleteBtn = new JButton("Удалить") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("удалить.фигуру", rectangle);
                    }

                }
            }
        };
        add(deleteBtn);
    }

    @Override
    public void оповестить(String eventName, Object... аргументы) {
        Shape shape = (Shape) аргументы[0];
        if (shape instanceof Rectangle) {
            rectangle = (Rectangle) shape;
            numberField.setText(rectangle.getNumber());
            nameField.setText(rectangle.getName());
        }

    }
}
