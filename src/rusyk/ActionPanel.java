package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.charts.SimpleChart;
import rusyk.figures.InvisibleRectangle;
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
    // размер кнопок
    int buttonDimensionX = 130;
    int buttonDimensionY = 20;

    // поле "Номер блока"
    JLabel numberLabel;
    JTextField numberField;

    // поле "Название блока"
    JLabel nameLabel;
    JTextField nameField;

    // названия прикрепленных файлов
    JLabel fileChartNameLabel;
    JLabel fileInfoNameLabel;

    // поле для загрузки файла в фигуру
    JButton addСhartFile;
    JButton addInfoFile;

    JButton saveBtn;
    JButton deleteBtn;
    JButton chartBtn;
    JButton infoBtn;
    Rectangle rectangle;
    Shape shape;

    public ActionPanel() {

        this.mainFrame = mainFrame;

        СобытийнаяШина.подписатьсяНаСобытие("shape selection", this);
        СобытийнаяШина.подписатьсяНаСобытие("построить.график", this);

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


        fileChartNameLabel = new JLabel();
        fileChartNameLabel.setVisible(false);
        add(fileChartNameLabel);

        // поле для загрузки файла отсчетов в фигуру

        addСhartFile = new JButton("Доб. файл отсчетов") {
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
                            rectangle.addFileChart(file);
                            fileChartNameLabel.setText(rectangle.getFileChartName());
                            if (file.getContent() != null) {
                                chartBtn.setVisible(true);
                            }
                        }
                    }
                }
            }
        };
        addСhartFile.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        addСhartFile.setVisible(false);
        add(addСhartFile);

        fileInfoNameLabel = new JLabel();
        fileInfoNameLabel.setVisible(false);
        add(fileInfoNameLabel);

        addInfoFile = new JButton("Добавить инфо") {
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
                            rectangle.addFileInfo(file);
                            fileInfoNameLabel.setText(rectangle.getFileInfoName());
                            if (file.getContent() != null) {
                                infoBtn.setVisible(true);
                            }
                        }
                    }
                }
            }
        };
        addInfoFile.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        addInfoFile.setVisible(false);
        add(addInfoFile);

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
        saveBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        saveBtn.setVisible(false);
        add(saveBtn);

        deleteBtn = new JButton("Удалить элемент") {
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
        deleteBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        deleteBtn.setVisible(false);
        add(deleteBtn);

        chartBtn = new JButton("Посроить график") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("построить.график", rectangle);
                    } else if (shape != null) {
                        СобытийнаяШина.опубликоватьСобытие("построить.график", shape);
                    }

                }
            }
        };
        chartBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        chartBtn.setVisible(false);
        add(chartBtn);

        infoBtn = new JButton("Открыть инфо") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        //СобытийнаяШина.опубликоватьСобытие("построить.график", rectangle);
                    } else if (shape != null) {
                        //СобытийнаяШина.опубликоватьСобытие("построить.график", shape);
                    }

                }
            }
        };
        infoBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        infoBtn.setVisible(false);
        add(infoBtn);
    }

    @Override
    public void оповестить(String eventName, Object... аргументы) {

        Shape shape = (Shape) аргументы[0];
        if (shape instanceof InvisibleRectangle) {
            this.rectangle = (InvisibleRectangle)shape;
            UploadedFile fileInfo = rectangle.getFileInfo();
            numberLabel.setVisible(false);
            numberField.setVisible(false);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            fileChartNameLabel.setVisible(false);
            fileInfoNameLabel.setVisible(true);
            saveBtn.setVisible(true);
            addСhartFile.setVisible(false);
            addInfoFile.setVisible(true);
            deleteBtn.setVisible(false);
            chartBtn.setVisible(false);
            if (fileInfo.getContent() != null) {
                infoBtn.setVisible(true);
            } else {
                infoBtn.setVisible(false);
            }
            nameField.setText(rectangle.getName());
            fileInfoNameLabel.setText(rectangle.getFileInfoName());
        } else if (shape instanceof Rectangle) {
            this.rectangle = (Rectangle) shape;
            UploadedFile fileChart = rectangle.getFileChart();
            UploadedFile fileInfo = rectangle.getFileInfo();
            this.shape = null;
            numberLabel.setVisible(true);
            numberField.setVisible(true);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            fileChartNameLabel.setVisible(true);
            fileInfoNameLabel.setVisible(true);
            saveBtn.setVisible(true);
            addСhartFile.setVisible(true);
            addInfoFile.setVisible(true);
            deleteBtn.setVisible(true);

            if (fileChart.getContent() != null) {
                chartBtn.setVisible(true);
            } else {
                chartBtn.setVisible(false);
            }
            if (fileInfo.getContent() != null) {
                infoBtn.setVisible(true);
            } else {
                infoBtn.setVisible(false);
            }

            numberField.setText(rectangle.getNumber());
            nameField.setText(rectangle.getName());
            fileChartNameLabel.setText(rectangle.getFileChartName());
            fileInfoNameLabel.setText(rectangle.getFileInfoName());


            if (eventName.equals("построить.график")) {
                String blockName = rectangle.getName();
                if (fileChart.getContent() != null) {
                    SimpleChart chart = new SimpleChart(blockName, fileChart);
                }
            }


        } else {
            this.rectangle = null;
            this.shape = shape;
            numberLabel.setVisible(false);
            numberField.setVisible(false);
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            fileChartNameLabel.setVisible(false);
            fileInfoNameLabel.setVisible(false);
            saveBtn.setVisible(false);
            addСhartFile.setVisible(false);
            addInfoFile.setVisible(false);
            deleteBtn.setVisible(true);
            chartBtn.setVisible(false);
            infoBtn.setVisible(false);
        }
    }
}
