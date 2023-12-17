import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Scanner;

public class IOExample {
    
    public static void main(String[] args) {

        // File I/O
        File file = new File("testIO.txt");

        if (file.exists()) {
            System.out.println("File exists");

            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getPath());
            System.out.println("Is Directory: " + file.isDirectory());
        } else {
            System.out.println("File doesn't exists");
        }

        // 讀出檔案內容
        // Way1: FileInputStream
        try {
            FileInputStream fis = new FileInputStream("testIO.txt");

            while (fis.available() > 0) {
                System.out.print((char)fis.read());
            }
            fis.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

        // Way2: FileReader
        try {
            FileReader fr = new FileReader("testIO.txt");

            while (fr.ready()) {
                System.out.print((char)fr.read());
            }
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

        // Way3: BufferedReader
        try {
            BufferedReader br = new BufferedReader(new FileReader("testIO.txt"));

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

        // Way4: Scanner
        try{
            Scanner sc = new Scanner(new File("testIO.txt"));
            while(sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

}
