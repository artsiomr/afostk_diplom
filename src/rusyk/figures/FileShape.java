package rusyk.figures;

import rusyk.UploadedFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.05.13
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class FileShape extends NumberShape {

    private UploadedFile file = new UploadedFile();

    public UploadedFile getFile() {
        return file;
    }

    public String getFileName() {
        return file.getFileName();
    }

    public void addFile(UploadedFile newFile) {
        file = newFile;
    }
}
