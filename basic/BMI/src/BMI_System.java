import java.util.Scanner;

public class BMI_System {
    public static void main(String[] args) {

        // 第一個使用者
        User user1 = new User(
            args[0], Float.parseFloat(args[1]), Integer.parseInt(args[2]));

        Scanner scanner = new Scanner(System.in);

        // 使用者輸入第二個使用者資料
        System.out.println("Please input user name: ");
        String u2_name = scanner.nextLine();
        System.out.println("Please input user height: ");
        Float u2_height = scanner.nextFloat();
        System.out.println("Please input user weight: ");
        int u2_weight = scanner.nextInt();

        // 第二個使用者
        User user2 = new User(u2_name, u2_height, u2_weight);

        // 計算使用者的BMI
        user1.BMI_calculate();
        user2.BMI_calculate();

        // 列印使用者資訊
        System.out.println(user1.toString());
        System.out.println(user2.toString());

        // 更正第一位使用者體重
        System.out.println("Input User1 new weight: ");
        user1.setWeight(scanner.nextInt());

        // 列印更正後資訊
        user1.BMI_calculate();
        System.out.println(user1.toString());

        scanner.close();
    }
}