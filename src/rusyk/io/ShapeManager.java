package rusyk.io;

import rusyk.figures.Shape;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public class ShapeManager {

    public void save(List<Shape> shapes, File outFile) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(outFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(shapes);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot save shapes", e);
        }
    }

    public List<Shape> load(File inFile) {
        try {
            InputStream inputStream = new FileInputStream(inFile);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            return  (List<Shape>) in.readObject();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load shapes", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot load shapes", e);
        }
    }
}
