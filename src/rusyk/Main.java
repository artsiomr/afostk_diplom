package rusyk;

import javax.swing.*;

/**
 * @author Maksim Turchyn
 */
public class Main {

    public static void main(String[] args) {
        new MainFrame();
        JFrame i = new JFrame();
        i.setAlwaysOnTop(true);
        i.toFront();
    }
}
