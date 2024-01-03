import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Bulletin {

    static Scanner scanner = new Scanner(System.in);
    
    // 主程式進入點
    public static void main(String[] args) {
        
        String input = showMenu();  // 顯示主選單
        do {
            try {   // 偵錯 -> 若是發生錯誤則印出
                switch (input) {
                    case "1":   // 功能一: 留言
                        leaveComment();
                        break;
                    case "2":   // 功能二: 顯示留言
                        listComment();
                        break;
                    case "0":   // 功能零: 離開
                        break;
                    default:    // 其他選項
                        System.out.println("請輸入範圍內數值!");
                }
            } catch (Exception e) {
                System.out.println("錯誤發生: " + e); 
            }
            input = showMenu();
        } while (!input.equals("0"));   // 執行直到選項0(離開)為止
    }

    // 獲取資料夾目錄
    static public Path getPath() {
        File data = new File("data");
        Path path = data.toPath();
        // 若是檔案不存在或不為目錄則建立資料夾
        if (!data.exists() || !data.isDirectory())
            data.mkdir();
        return path;
    }

    // 顯示主選單
    static public String showMenu() {
        System.out.println("≡≡≡ Welcome to Bulletin Board ≡≡≡");
        System.out.println("1) Leave a Comment");
        System.out.println("2) List all Comments");
        System.out.println("0) Exit");
        System.out.print("Please select a number in [1,2,0]: ");
        return scanner.nextLine();
    }

    // 留言
    static public void leaveComment() throws Exception {
        // 獲取留言者基本資訊與留言內容
        System.out.print("What is your name: ");
        String name = scanner.nextLine();
        System.out.println("Leave your message here: ");
        String message = scanner.nextLine();
        System.out.println("Your Comment was Saved!");

        Comment comment = new Comment(name, new Date(), message);

        // 將留言寫入.ser檔中 -> 若是已存在則讀取出來新增; 反之則建立
        File file = new File(getPath()+"/"+comment.getName()+".ser");
        List<Comment> comments;
        if (file.exists()) {    
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            comments = (List<Comment>) ois.readObject();
            ois.close();
        } else {
            comments = new ArrayList<>();
            file.createNewFile();
        }
        comments.add(comment);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(comments);
        oos.close();                           
    }

    // 列印留言紀錄
    static public void listComment() throws Exception {
        DirectoryStream<Path> stream = Files.newDirectoryStream(getPath());
        for (Path s : stream) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(s.toFile()));
            List<Comment> comments = (List<Comment>) ois.readObject();
            comments.forEach(c -> System.out.println(c.toString()));
        }
    }
}
