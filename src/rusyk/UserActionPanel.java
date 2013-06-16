package rusyk;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 10.06.13
 * Time: 9:33
 * To change this template use File | Settings | File Templates.
 */

import rusyk.bus.ШинныйПодписчик;

import javax.swing.*;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.buttons.FigureButton;
import rusyk.charts.SimpleChart;
import rusyk.figures.InvisibleRectangle;
import rusyk.figures.Rectangle;
import rusyk.figures.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 08.05.13
 * Time: 9:37
 * To change this template use File | Settings | File Templates.
 */
public class UserActionPanel extends JPanel implements ШинныйПодписчик {

    private MainFrame mainFrame;
    // размер кнопок
    int buttonDimensionX = 160;
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
    JLabel mathcadFileName;

    // поле для загрузки файла в фигуру
    JButton addСhartFile;
    JButton addInfoFile;
    JButton addMathcadFileName;

    // кнопки сохранить - удалить
    JButton saveBtn;
    JButton deleteBtn;

    // кнопка Построить график
    JButton chartBtn;

    // кнопка Отобразить параметрически
    JButton paramBtn;
    JLabel blockNumberLabel;
    JTextField blockNumberField;
    JButton blockNumberBtn;

    // кнопка Отобразить информацию
    JButton infoBtn;

    // кнопка открыть файл маткада
    JButton mathcadBtn;

    JLabel chartNameMcd;
    JButton openChartMcd;

    JButton sendChart;

    Rectangle rectangle;
    Shape shape;

    public UserActionPanel() {

        СобытийнаяШина.подписатьсяНаСобытие("shape selection", this);
        СобытийнаяШина.подписатьсяНаСобытие("построить.график", this);
        СобытийнаяШина.подписатьсяНаСобытие("построить.график.mcd", this);
        СобытийнаяШина.подписатьсяНаСобытие("отобразить.параметрически", this);
        СобытийнаяШина.подписатьсяНаСобытие("отправить.на.осциллограф", this);

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
        nameField.setColumns(14);
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

        mathcadFileName = new JLabel();
        mathcadFileName.setVisible(false);
        add(mathcadFileName);

        addMathcadFileName = new JButton("Доб. шаблон mathcad") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        JFileChooser fileChooser = new JFileChooser();
                        // Demonstrate "Open" dialog:
                        int rVal = fileChooser.showOpenDialog(mainFrame);
                        if (rVal == JFileChooser.APPROVE_OPTION) {
                            if (rectangle instanceof InvisibleRectangle) {
                                UploadedFile file = new UploadedFile(fileChooser.getSelectedFile());
                                ((InvisibleRectangle) rectangle).setMathcadFileName(file.getFileName());
                                mathcadFileName.setText(((InvisibleRectangle) rectangle).getMathcadFileName());
                                if (mathcadFileName.getText() != "") {
                                    mathcadFileName.setVisible(true);
                                }
                            }
                        }
                    }
                }
            }
        };
        addMathcadFileName.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        addMathcadFileName.setVisible(false);
        add(addMathcadFileName);


        mathcadBtn = new JButton("Открыть mathcad") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle.getFileInfoName() != null) {
                        try {
                            if (rectangle instanceof InvisibleRectangle) {
                                String fileName = ((InvisibleRectangle) rectangle).getMathcadFileName();
                                File f = new File(fileName);
                                Desktop.getDesktop().open(f);
                                mathcadBtn.setVisible(true);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (shape != null) {
                    }

                }
            }
        };

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
                    if (rectangle.getFileInfoName() != null) {
                        try {
                            String property = "java.io.tmpdir";
                            String tempDir = System.getProperty(property);
                            tempDir += new String("afostk\\");
                            String path = tempDir;
                            String fileName = rectangle.getFileInfoName();
                            File f = new File(path + fileName);
                            f.createNewFile();
                            FileOutputStream outputStream = new FileOutputStream(f);
                            outputStream.write(rectangle.getFileInfo().getContent());
                            Desktop.getDesktop().open(f);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

        paramBtn = new JButton("Отобр. параметр. с") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("отобразить.параметрически", rectangle);
                    }

                }
            }
        };
        paramBtn.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        paramBtn.setVisible(false);
        add(paramBtn);

        blockNumberLabel = new JLabel("Введите номер блока: ");
        blockNumberField = new JTextField();
        blockNumberField.setColumns(3);

        blockNumberBtn = new JButton("Ок") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (blockNumberField.getText() != null) {
                        СобытийнаяШина.опубликоватьСобытие("построить.параметрическую.зависимость", rectangle, blockNumberField.getText());
                    }
                }
            }
        };
        blockNumberBtn.setPreferredSize(new Dimension(50, 20));

        blockNumberLabel.setVisible(false);
        blockNumberField.setVisible(false);
        blockNumberBtn.setVisible(false);

        add(blockNumberLabel);
        add(blockNumberField);
        add(blockNumberBtn);

        chartNameMcd = new JLabel();
        openChartMcd = new JButton("Открыть график mcd") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("построить.график.mcd", rectangle);
                    }
                }
            }
        };
        openChartMcd.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));

        chartNameMcd.setVisible(false);
        openChartMcd.setVisible(false);

        add(chartNameMcd);
        add(openChartMcd);

        sendChart = new JButton("На осциллограф") {
            @Override
            protected void processMouseEvent(MouseEvent mouseEvent) {
                super.processMouseEvent(mouseEvent);
                if (MouseEvent.MOUSE_CLICKED == mouseEvent.getID()) {
                    if (rectangle != null) {
                        СобытийнаяШина.опубликоватьСобытие("отправить.на.осциллограф", rectangle);
                    }
                }
            }
        };
        sendChart.setPreferredSize(new Dimension(buttonDimensionX, buttonDimensionY));
        sendChart.setVisible(false);

        add(sendChart);


    }

    @Override
    public void оповестить(String eventName, Object... аргументы) {
        Shape shape = (Shape) аргументы[0];
        if (shape instanceof InvisibleRectangle) {
            this.rectangle = (InvisibleRectangle)shape;
            UploadedFile fileInfo = rectangle.getFileInfo();
            numberLabel.setVisible(false);
            numberField.setVisible(false);
            fileChartNameLabel.setVisible(false);
            addСhartFile.setVisible(false);
            deleteBtn.setVisible(false);
            chartBtn.setVisible(false);
            paramBtn.setVisible(false);
            blockNumberLabel.setVisible(false);
            blockNumberField.setVisible(false);
            blockNumberBtn.setVisible(false);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            fileInfoNameLabel.setVisible(true);
            addMathcadFileName.setVisible(true);
            saveBtn.setVisible(true);
            addInfoFile.setVisible(true);


            if (!mathcadFileName.getText().equals("")) {
                mathcadFileName.setVisible(false);
                mathcadBtn.setVisible(false);
            } else {
                mathcadFileName.setVisible(true);
                mathcadBtn.setVisible(true);
            }

            if (fileInfo.getContent() != null) {
                infoBtn.setVisible(true);
            } else {
                infoBtn.setVisible(false);
            }

            nameField.setText(rectangle.getName());
            fileInfoNameLabel.setText(rectangle.getFileInfoName());

            chartNameMcd.setVisible(false);
            openChartMcd.setVisible(false);
            sendChart.setVisible(false);

        } else if (shape instanceof Rectangle) {

            this.rectangle = (Rectangle) shape;
            UploadedFile fileChart = rectangle.getFileChart();
            UploadedFile fileInfo = rectangle.getFileInfo();
            this.shape = null;

            mathcadFileName.setVisible(false);
            addMathcadFileName.setVisible(false);
            mathcadBtn.setVisible(false);

            numberLabel.setVisible(true);
            numberField.setVisible(true);
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            fileChartNameLabel.setVisible(true);
            fileInfoNameLabel.setVisible(true);
            saveBtn.setVisible(true);
            addInfoFile.setVisible(true);
            addСhartFile.setVisible(true);
            deleteBtn.setVisible(true);


            if (fileChart.getContent() != null) {
                chartBtn.setVisible(true);
                paramBtn.setVisible(true);
            } else {
                chartBtn.setVisible(false);
                paramBtn.setVisible(false);
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

            File currentDirectory = new File("");
            System.out.println(currentDirectory.getAbsolutePath());

            if (eventName.equals("построить.график")) {
                String blockName = rectangle.getName();
                if (fileChart.getContent() != null) {
                    SimpleChart chart = new SimpleChart(blockName, fileChart);
                }
            } else if (eventName.equals("отобразить.параметрически")) {
                System.out.print("Зашло в отобразить.параметрически");
                blockNumberLabel.setVisible(true);
                blockNumberField.setVisible(true);
                blockNumberBtn.setVisible(true);
            } else if (eventName.equals("построить.график.mcd")) {
                System.out.print("Зашло в построить.график.mcd");

                String fullFilePath = String.valueOf(currentDirectory.getAbsoluteFile()) + "\\" + rectangle.getBlockNumber() + ".dat";
                File f = new File(fullFilePath);
                UploadedFile file = new UploadedFile(f);

                String blockName = rectangle.getName();
                if (file.getContent() != null) {
                    SimpleChart chart = new SimpleChart(blockName, file);
                }
            } else if (eventName.equals("отправить.на.осциллограф")) {
                System.out.print("Зашло в отправить.на.осциллограф");

                try {
                    String fullFilePath = String.valueOf(currentDirectory.getAbsoluteFile()) + "\\" + rectangle.getBlockNumber() + ".dat";
                    File fileFrom = new File(fullFilePath);

                    if(fileFrom.exists()){
                        File fileTo = new File(String.valueOf(currentDirectory.getAbsoluteFile()) + "\\" + "G6_45.dat");

                        fileTo.delete();

                        if(fileTo.createNewFile()) {
                            FileOutputStream outputStream = new FileOutputStream(fileTo);
                            outputStream.write(read(fileFrom));
                            outputStream.close();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            if((rectangle != null) && (rectangle.getBlockNumber() != "") && (rectangle.getBlockNumber() != "0")) {
                String fullFilePath = String.valueOf(currentDirectory.getAbsoluteFile()) + "\\" + rectangle.getBlockNumber() + ".dat";
                System.out.println(fullFilePath);
                File f = new File(fullFilePath);
                if(f.exists()) {
                    chartNameMcd.setText(rectangle.getBlockNumber() + ".dat");
                    chartNameMcd.setVisible(true);
                    openChartMcd.setVisible(true);
                    sendChart.setVisible(true);
                } else {
                    chartNameMcd.setVisible(false);
                    openChartMcd.setVisible(false);
                    sendChart.setVisible(false);
                }
            } else {
                chartNameMcd.setVisible(false);
                openChartMcd.setVisible(false);
                sendChart.setVisible(false);
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
            mathcadFileName.setVisible(false);
            addMathcadFileName.setVisible(false);
            mathcadBtn.setVisible(false);
            saveBtn.setVisible(false);
            addСhartFile.setVisible(false);
            addInfoFile.setVisible(false);
            chartBtn.setVisible(false);
            paramBtn.setVisible(false);
            infoBtn.setVisible(false);

            blockNumberLabel.setVisible(false);
            blockNumberField.setVisible(false);
            blockNumberBtn.setVisible(false);

            chartNameMcd.setVisible(false);
            openChartMcd.setVisible(false);
            sendChart.setVisible(false);
            deleteBtn.setVisible(true);
        }
    }

    private byte[] read(File file) {
        InputStream ios = null;
        ByteArrayOutputStream ous = null;
        try {
            byte[] buffer = new byte[10240];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }
}
