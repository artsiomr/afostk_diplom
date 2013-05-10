package rusyk.io;

import rusyk.figures.Shape;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Maksim Turchyn
 */
public class ShapeManager {

    public void save(List<Shape> shapes, OutputStream outputStream) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(shapes);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot save shapes", e);
        }
    }

    public List<Shape> load(InputStream inputStream)  {
        try {
            ObjectInputStream in = new ObjectInputStream(inputStream);
            return  (List<Shape>) in.readObject();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load shapes", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Cannot load shapes", e);
        }
    }
}
