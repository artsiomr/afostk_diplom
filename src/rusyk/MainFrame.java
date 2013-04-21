package rusyk;

import rusyk.event.DrawEvent;
import rusyk.figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Maksim Turchyn
 */
public class MainFrame extends JFrame implements DrawEvent.DrawEventListener {

    private DrawPanel drawPanel;
    private DrawPanel xPanel;
    private DrawPanel yPanel;
    private ButtonPanel btnPanel = new ButtonPanel();

    public MainFrame() {
        super("Автоматизация формального описания систем телекоммуникаций");
        pack();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        //setSize(xSize, ySize);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        
        btnPanel.setPreferredSize(new Dimension(150, 710));
        btnPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnPanel.addDrawEventListener(this);
        add(btnPanel);
        
        drawPanel = new DrawPanel();
        //drawPanel.setPreferredSize(new Dimension(xSize - 200, ySize));
        drawPanel.setPreferredSize(new Dimension(900+40+40, 630+40+40));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        drawPanel.addMouseListener(btnPanel);
        add(drawPanel);
        
        setVisible(true);
    }

    @Override
    public void onDrawEvent(DrawEvent drawEvent) {
        drawPanel.draw(drawEvent.getShape());
    }
}
