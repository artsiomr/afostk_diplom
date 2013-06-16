package rusyk;

import rusyk.bus.СобытийнаяШина;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 01.06.13
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
public class Login extends JPanel implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final JFrame frame = new JFrame("Авторизация");
        JPanel panel = new JPanel();
        final JPasswordField passField = new JPasswordField();
        passField.setColumns(10);
        JButton ok = new JButton("Логин");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ("123456".equals(new String(passField.getPassword()))) {
                    СобытийнаяШина.опубликоватьСобытие("изменение.роли", true);
                    frame.setVisible(false);
                }
            }
        });

        panel.setPreferredSize(new Dimension(100, 100));
        frame.setPreferredSize(new Dimension(100, 100));
        frame.setLocation(400, 300);
        frame.setMinimumSize(new Dimension(100, 100));
        frame.setMaximumSize(new Dimension(100, 100));

        frame.add(panel);
        panel.add(passField);
        panel.add(ok);
        frame.setVisible(true);
    }
}
