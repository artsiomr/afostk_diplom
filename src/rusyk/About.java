package rusyk;

import rusyk.bus.СобытийнаяШина;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 01.06.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class About extends JPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JFrame frame = new JFrame("Разработчик");
        JPanel panel = new JPanel();

        String title = new String("БГУИР \n" +
                "- Кафедра систем телекоммуникаций");
        JTextArea jta = new JTextArea("Рысюк Артем Васильевич\n" +
                "Выпускник БГУИР ФТК");
        panel.setBorder(BorderFactory.createTitledBorder(title));
        jta.setLineWrap(true);
        jta.setSize(new Dimension(250, 250));
        jta.setBackground(panel.getBackground());

        panel.add(jta);
        panel.setPreferredSize(new Dimension(300, 300));
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setLocation(300, 150);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setMaximumSize(new Dimension(300, 300));
        frame.setVisible(true);
    }
}
