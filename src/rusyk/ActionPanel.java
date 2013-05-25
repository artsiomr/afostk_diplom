package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.figures.InvisibleRectangle;
import rusyk.figures.Line;
import rusyk.figures.Rectangle;
import rusyk.figures.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 08.05.13
 * Time: 9:37
 * To change this template use File | Settings | File Templates.
 */
public class ActionPanel extends JPanel implements ШинныйПодписчик {

    private MainFrame mainFrame;

    // поле "Номер блока"
    JLabel numberLabel;
    JTextField numberField;

    // поле "Название блока"
    JLabel nameLabel;
    JTextField nameField;

    // названия прикрепленных файлов
    JLabel filenameLabel;
    private String filenames = "";

    // поле для загрузки файла в фигуру
    JButton addFile;

    JButton saveBtn;
    JButton deleteBtn;
    Rectangle rectangle;
    Shape shape;

    public ActionPanel() {

        this.mainFrame = mainFrame;

        СобытийнаяШина.подписатьсяНаСобытие("shape selection", this);

        JLabel fieldLabel = new JLabel("Доступные действия:");
        add(fieldLabel);

        // поле "Номер блока"
        numberLabel = new JLabel("Номер блока: ");
        numberField = new JTextField();
        numberField.setColumns(10);
        numberLabel.setVisible(false);
        numberField.setVisible(false);
        add(numberLabel);
        add(numberField);

        // поле "Название блока"
        nameLabel = new JLabel("Название блока: ");
        nameField = new JTextField();
        nameField.setColumns(10);
        nameLabel.setVisible(false);
        nameField.setVisible(false);
        add(nameLabel);
        add(nameField);


        filenameLabel = new JLabel();
        filenameLabel.setVisible(false);
        if (rectangle != null) {
            filenameLabel.setText(rectangle.getFileNames());
        }
        add(filenameLabel);

        // поле для загрузки файла в фигуру

        addFile = new JButton("Добавить файл") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        JFileChooser fileChooser = new JFileChooser();
                        // Demonstrate "Open" dialog:
                        int rVal = fileChooser.showOpenDialog(mainFrame);
                        if (rVal == JFileChooser.APPROVE_OPTION) {
                            //
                            UploadedFile file = new UploadedFile(fileChooser.getSelectedFile());
                            rectangle.addFile(file);
                        }
                    }
                }
            }
        };
        addFile.setVisible(false);
        add(addFile);

        saveBtn = new JButton("Сохранить") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        rectangle.setName(nameField.getText());
                        if (numberField.isVisible()) {
                            rectangle.setNumber(numberField.getText());
                        }
                    }
                    СобытийнаяШина.опубликоватьСобытие("перерисовать.фигуры");
                }
            }
        };
        saveBtn.setVisible(false);
        add(saveBtn);

        deleteBtn = new JButton("Удалить") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("удалить.фигуру", rectangle);
                    } else if (shape != null) {
                        СобытийнаяШина.опубликоватьСобытие("удалить.фигуру", shape);
                    }

                }
            }
        };
        deleteBtn.setVisible(false);
        add(deleteBtn);
    }

    @Override
    public void оповестить(String eventName, Object... аргументы) {
        Shape shape = (Shape) аргументы[0];
        if (shape instanceof InvisibleRectangle) {
            this.rectangle = (InvisibleRectangle)shape;
            numberLabel.setVisible(false);
            numberField.setVisible(false);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            filenameLabel.setVisible(false);
            saveBtn.setVisible(true);
            addFile.setVisible(false);
            deleteBtn.setVisible(false);
            nameField.setText(rectangle.getName());
        } else if (shape instanceof Rectangle) {
            this.rectangle = (Rectangle) shape;
            this.shape = null;
            numberLabel.setVisible(true);
            numberField.setVisible(true);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            filenameLabel.setVisible(true);
            saveBtn.setVisible(true);
            addFile.setVisible(true);
            deleteBtn.setVisible(true);
            numberField.setText(rectangle.getNumber());
            nameField.setText(rectangle.getName());
        } else {
            this.rectangle = null;
            this.shape = shape;
            numberLabel.setVisible(false);
            numberField.setVisible(false);
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            filenameLabel.setVisible(false);
            saveBtn.setVisible(false);
            addFile.setVisible(false);
            deleteBtn.setVisible(true);
        }
    }
}
