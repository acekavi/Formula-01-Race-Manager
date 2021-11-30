import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    public void writeObjectToFile(String filepath,Object toWriteObject) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Saves the arraylist in bytecode using Serializers
            objectOut.writeObject(toWriteObject);
            objectOut.close();
//            System.out.println("File has been saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList readObjectFile(String filepath) {
        try {

            // Reads the bytecode in the file to an arraylist and returns it
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ArrayList obj = (ArrayList)objectIn.readObject();
//            System.out.println("File has been read successfully!");
            objectIn.close();
            return obj;

        } catch (Exception ex) {
            System.out.println("File is empty or unavailable!");
            return new ArrayList();
        }
    }
}
