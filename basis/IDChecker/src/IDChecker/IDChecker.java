package IDChecker;

import java.util.Scanner;

public class IDChecker {

    static Scanner scanner = new Scanner(System.in, "UTF-8");

    // 主程式
    public static void main(String[] args) {

        // 列印主選單
        String input = Menu();
        do {
            // 功能一
            if (input.equals("1")) {

                System.out.print("請輸入身分證字號: ");
                String id = scanner.nextLine();
                
                ID.verify(id);

            // 功能二
            } else if (input.equals("2")) {

                System.out.print("請輸入縣市: ");
                String city = scanner.nextLine();
                System.out.println("city: " + city);
                System.out.print("請輸入性別(男/女): ");
                String gender = scanner.nextLine();
                System.out.println("gender: " + gender);

                ID.generator(city, gender);;

            // 離開
            } else if (input.equals("0")) {
                continue;

            // 選項外輸入
            } else {
                System.out.println("無此選項!");
            }

            input = Menu();

        } while (!input.equals("0"));
        
    }

    // 功能: 印出選單，並回傳輸入值
    public static String Menu() {

        System.out.println("******** Menu ********");
        System.out.println("1) 驗證身分證字號");
        System.out.println("2) 產生身分證字號");
        System.out.println("0) 離開");
        System.out.println("**********************");
        System.out.print("請選擇: ");

        String input = scanner.nextLine();

        return input;
    }
}


