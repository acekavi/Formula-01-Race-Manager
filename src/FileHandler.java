import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    public void WriteObjectToFile(String filepath,Object toWriteObject) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(toWriteObject);
            objectOut.close();
            System.out.println("File has been saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList ReadObjectFromFile(String filepath) {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            ArrayList obj = (ArrayList)objectIn.readObject();
            System.out.println("File has been read successfully!");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
