import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {
    // 使用者列表
    static List<Account> users = new ArrayList<Account>();
    // 變數(是否繼續)
    static boolean keep;
    // 變數(使用者輸入)
    static String input;

    /* 主程式 */
    public static void main(String[] args) {

        /* 預設參數 */
        Scanner scanner = new Scanner(System.in);
        // 使用者選單
        String menu = "************ Menu ************\n" +
                "0) Deposit\n" +
                "1) Withdraw\n" +
                "2) Transfer\n" +
                "3) Display Account Information\n" +
                "4) Logout\n" +
                "******************************";
        // 預設使用者
        users.add(new Account("Mary", "0000", 100));
        users.add(new Account("Sam", "1234", -10));
        users.add(new Account("David", "5678", 80));

        /* 登入資訊 */
        Integer id = Login(scanner);
        keep = true;
        
        while (id != null && keep == true) {

            /* 使用者選單 */
            System.out.println();
            System.out.println(menu);
            System.out.print("Please enter a number in [0,1,2,3,4]: ");
            input = scanner.next();

            switch (input) {
                case "0": // 存款
                    System.out.print("Please Enter the amount you want to deposit: ");
                    users.get(id).deposit(scanner.nextInt());
                    break;

                case "1": // 提款
                    System.out.print("Please Enter the amount you want to withdraw: ");
                    users.get(id).withdraw(scanner.nextInt());
                    break;

                case "2": // 匯款
                    System.out.print("Please Enter the user ID you want to transfer to: ");
                    int id2 = scanner.nextInt();
                    // 檢測收款使用者是否存在
                    if (id2 >= users.size()) {
                        System.out.println("User ID is not found.");
                        break;
                    }
                    System.out.print("Enter the amount you want to transfer: ");
                    int amount = scanner.nextInt();
                    users.get(id).transfer(users.get(id2), amount);
                    break;

                case "3": // 查詢使用者資料
                    users.get(id).showData();
                    break;

                case "4": // 登出
                    System.out.println("Goodbye " + users.get(id).getName() +
                            ". You've Logged Out successfully.");
                    keep = false;

                    // 是否再重新登入
                    System.out.println("If you want to try again, please press 1. Otherwise, please press 0.");
                    input = scanner.next();
                    if (input.equals("1")) {
                        id = Login(scanner);
                        keep = true;
                    }
            }
        }
        scanner.close();
    }

    /* 登入功能 */
    static Integer Login(Scanner scanner) {
        int id;
        String pw;
        keep = true;

        do {
            // 核對使用者ID
            System.out.print("Please Enter the User ID: ");
            id = scanner.nextInt();
            if (id >= users.size()) {
                System.out.println("User ID is not found.");
                keep = true;
                continue;
            }
            // 核對使用者Password
            System.out.print("Please Enter the Password: ");
            pw = scanner.next();
            if (!users.get(id).checkPassword(pw)) {
                System.out.println("Invalid Password!!!");

                // 是否要嘗試重新登入
                System.out.println("If you want to keep using this system, please press 1. Otherwise, please press 0.");
                input = scanner.next();
                if (input.equals("1")) {
                    continue;
                }
                keep = false;
            } else {
                keep = false;
                return id;
            }
        } while (keep == true);
        return null;
    }
}