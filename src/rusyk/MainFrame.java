package rusyk;

import rusyk.event.DrawEvent;

import javax.swing.*;
import java.awt.*;

/**
 * @author Maksim Turchyn
 */
public class MainFrame extends JFrame implements DrawEvent.DrawEventListener {

    private DrawPanel drawPanel;
    private ButtonPanel btnPanel = new ButtonPanel();
    private ActionPanel actPanel = new ActionPanel();

    public MainFrame() {
        super("Автоматизация формального описания систем телекоммуникаций");
        pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        setSize(xSize, ySize);

        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(dim.width, dim.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

        //setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));


        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Файл");
        JMenu menuInfo = new JMenu("Инфо");

        JMenuItem OpenMenuItem = new JMenuItem("Открыть");
        OpenMenuItem.addActionListener(new LoadDialog(this));
        menuFile.add(OpenMenuItem);

        JMenuItem SaveMenuItem = new JMenuItem("Сохранить как");
        SaveMenuItem.addActionListener(new SaveAsDialog(this));
        menuFile.add(SaveMenuItem);

        JMenuItem menuAbout = new JMenuItem("Разработчики");
        menuAbout.addActionListener(new About());
        menuInfo.add(menuAbout);

        menuBar.add(menuFile);
        menuBar.add(menuInfo);
        add(menuBar);
        setJMenuBar(menuBar);

        btnPanel.setPreferredSize(new Dimension(100, 680));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPanel.addDrawEventListener(this);
        add(btnPanel);
        
        drawPanel = new DrawPanel();
        //drawPanel.setPreferredSize(new Dimension(xSize - 200, ySize));
        drawPanel.setPreferredSize(new Dimension(900+40+40, 630+40+10));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drawPanel.addMouseListener(btnPanel);
        add(drawPanel);

        actPanel.setPreferredSize(new Dimension(170, 680));
        actPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(actPanel);


        
        setVisible(true);
    }

    @Override
    public void onDrawEvent(DrawEvent drawEvent) {
        drawPanel.draw(drawEvent.getShape());
    }
}
