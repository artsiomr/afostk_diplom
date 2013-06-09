package rusyk.charts;

import org.jfree.ui.RefineryUtilities;
import rusyk.UploadedFile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 02.06.13
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class ComplexChart {

    public ComplexChart(String s, String[][] values) {

        DrawGraph mainPanel = new DrawGraph(values);
        JFrame frame = new JFrame(s);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setBackground(Color.black);
        frame.getContentPane().setBackground(Color.black);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

    }
}
