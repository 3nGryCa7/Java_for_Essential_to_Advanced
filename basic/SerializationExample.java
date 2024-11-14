import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {

    public static void main(String[] args) {

        // Serialization
        try {
            FileOutputStream fos = new FileOutputStream("test.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject("Hello World");
            oos.writeObject(new Integer(123));
            oos.writeObject(new Double(123.456));
            oos.writeObject(new Boolean(true));

            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Deserialization
        try {
            FileInputStream fis = new FileInputStream("test.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());
            System.out.println(ois.readObject());

            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
