package rusyk.charts;

import org.jfree.ui.RefineryUtilities;
import rusyk.UploadedFile;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 26.05.13
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class SimpleChart {

    public UploadedFile file = new UploadedFile();

    public SimpleChart(String s, UploadedFile file) {

        XYSpline xyspline = new XYSpline(s, file);
        xyspline.pack();
        RefineryUtilities.centerFrameOnScreen(xyspline);
        xyspline.setVisible(true);

    }
}
