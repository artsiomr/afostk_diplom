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
public abstract class FileShape extends NamedShape {

    private List<UploadedFile> files = new ArrayList<UploadedFile>();

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void addFile(UploadedFile file) {
        files.add(file);
    }
}
