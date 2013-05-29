package rusyk.figures;

import rusyk.UploadedFile;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.05.13
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class FileShape extends NumberShape {

    private UploadedFile fileChart = new UploadedFile();
    private UploadedFile fileInfo = new UploadedFile();

    public UploadedFile getFileChart() {
        return fileChart;
    }

    public String getFileChartName() {
        return fileChart.getFileName();
    }

    public void addFileChart(UploadedFile newFile) {
        fileChart = newFile;
    }

    public UploadedFile getFileInfo() {
        return fileInfo;
    }

    public String getFileInfoName() {
        return fileInfo.getFileName();
    }

    public void addFileInfo(UploadedFile newFile) {
        fileInfo = newFile;
    }
}
