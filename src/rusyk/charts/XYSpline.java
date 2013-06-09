package rusyk.charts;

import java.awt.*;
import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.*;
import rusyk.UploadedFile;

public class XYSpline extends JFrame {

    public XYSpline(String title, UploadedFile file) {
        super(title);
        JPanel jpanel = createDemoPanel(title, file);
        jpanel.setPreferredSize(new Dimension(500, 270));
        getContentPane().add(jpanel);

    }

    public static JPanel createDemoPanel(String title, UploadedFile file) {

        NumberAxis numberaxis = new NumberAxis("t");
        numberaxis.setAutoRangeIncludesZero(false);
        NumberAxis numberaxis1 = new NumberAxis("A");
        numberaxis1.setAutoRangeIncludesZero(false);

        XYSplineRenderer xysplinerenderer = new XYSplineRenderer();

        XYSeries xyseries = new XYSeries(title);;

        //parse file here
        String otschety = file.getStringFileContent();
        String lines[] = otschety.split("\\r?\\n");

        int i = 0;
        for (String line : lines) {
            xyseries.add(i, Double.valueOf(line.trim()));
            i++;
        }

        XYSeriesCollection xyseriescollection = new XYSeriesCollection(xyseries);

        XYPlot xyplot = new XYPlot(xyseriescollection, numberaxis, numberaxis1, xysplinerenderer);
        xyplot.setBackgroundPaint(Color.YELLOW);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(2D, 2D, 2D, 2D));
        //JFreeChart jfreechart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);
        JFreeChart jfreechart = ChartFactory.createXYLineChart(title, "X", "Y", xyseriescollection, PlotOrientation.VERTICAL, true, true, false);
        ChartUtilities.applyCurrentTheme(jfreechart);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        return chartpanel;
    }
}
