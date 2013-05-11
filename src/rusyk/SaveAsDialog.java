package rusyk;

import rusyk.bus.СобытийнаяШина;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 10.05.13
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */
public class SaveAsDialog implements ActionListener {

    private MainFrame mainFrame;

    public SaveAsDialog(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        // Demonstrate "Save" dialog:
        int rVal = fileChooser.showSaveDialog(mainFrame);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            СобытийнаяШина.опубликоватьСобытие("сохранить.фигуры", fileChooser.getSelectedFile());
        }
    }
}
