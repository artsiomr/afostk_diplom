package rusyk;

import javax.print.DocFlavor;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 11.05.13
 * Time: 21:24
 * To change this template use File | Settings | File Templates.
 */
public class UploadedFile implements Serializable {

    private String fileName;
    private byte[] content;

    public UploadedFile() {
    }

    public UploadedFile(File file) {
        this.fileName = file.getName();
        this.content = read(file);
    }

    private byte[] read(File file) {
        InputStream ios = null;
        ByteArrayOutputStream ous = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
