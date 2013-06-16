package rusyk;

import rusyk.bus.СобытийнаяШина;
import rusyk.bus.ШинныйПодписчик;
import rusyk.event.DrawEvent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class MainFrame extends JFrame implements DrawEvent.DrawEventListener, ШинныйПодписчик {

    private DrawPanel drawPanel = new DrawPanel();
    private ButtonPanel btnPanel = new ButtonPanel();
    private JPanel userBtnPanel = new JPanel();
    private ActionPanel actPanel = new ActionPanel();
    private UserActionPanel userActionPanel = new UserActionPanel();

    public MainFrame() {
        super("Автоматизация формального описания систем телекоммуникаций");

        СобытийнаяШина.подписатьсяНаСобытие("изменение.роли", this);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Файл");
        JMenu menuInfo = new JMenu("Инфо");
        JMenu menuActions = new JMenu("Действие");

        JMenuItem OpenMenuItem = new JMenuItem("Открыть");
        OpenMenuItem.addActionListener(new LoadDialog(this));
        menuFile.add(OpenMenuItem);

        JMenuItem SaveMenuItem = new JMenuItem("Сохранить как");
        SaveMenuItem.addActionListener(new SaveAsDialog(this));
        menuFile.add(SaveMenuItem);

        JMenuItem menuAbout = new JMenuItem("Разработчики");
        menuAbout.addActionListener(new About());
        menuInfo.add(menuAbout);

        JMenuItem menuAdmin = new JMenuItem("Администратор");
        menuAdmin.addActionListener(new Login());
        menuActions.add(menuAdmin);

        menuBar.add(menuFile);
        menuBar.add(menuInfo);
        menuBar.add(menuActions);
        add(menuBar);
        setJMenuBar(menuBar);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize, ySize);

        int mainPanelWidth = 956;
        int leftMenuWidth = 120;
        int rightMenuWidth = 170;
        ySize = 688;

        System.out.println(mainPanelWidth);
        System.out.println(ySize);

        btnPanel.setPreferredSize(new Dimension(leftMenuWidth, ySize));
        userBtnPanel.setPreferredSize(new Dimension(leftMenuWidth, ySize));
        drawPanel.setPreferredSize(new Dimension(mainPanelWidth, ySize));
        actPanel.setPreferredSize(new Dimension(rightMenuWidth, ySize));
        userActionPanel.setPreferredSize(new Dimension(rightMenuWidth, ySize));

        btnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPanel.addDrawEventListener(this);
        btnPanel.setVisible(false);
        //userBtnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userBtnPanel.setVisible(true);

        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drawPanel.addMouseListener(btnPanel);

        actPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        userActionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(btnPanel);
        add(userBtnPanel);
        add(drawPanel);
        add(actPanel);
        add(userActionPanel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        pack();

        if (ВластелинРоли.getInstance().isAdmin()) {
            btnPanel.setVisible(true);
            actPanel.setVisible(true);
            userBtnPanel.setVisible(false);
            userActionPanel.setVisible(false);
        } else {
            btnPanel.setVisible(false);
            actPanel.setVisible(false);
            userBtnPanel.setVisible(true);
            userActionPanel.setVisible(true);
        }
    }

    @Override
    public void onDrawEvent(DrawEvent drawEvent) {
        drawPanel.draw(drawEvent.getShape());
    }

    @Override
    public void оповестить(String имяСобытия, Object... аргументы) {
        if(имяСобытия.equals("изменение.роли")) {
            if((Boolean)аргументы[0]) {
                btnPanel.setVisible(true);
                actPanel.setVisible(true);
                userBtnPanel.setVisible(false);
                userActionPanel.setVisible(false);
            } else {
                btnPanel.setVisible(false);
                actPanel.setVisible(false);
                userBtnPanel.setVisible(true);
                userActionPanel.setVisible(true);
            }
        }
    }
}
