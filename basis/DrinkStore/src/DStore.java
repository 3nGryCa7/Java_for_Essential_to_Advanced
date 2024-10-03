package DrinkStore;

import java.util.Scanner;

public class DStore {
    
    static Scanner scanner = new Scanner(System.in);
    static Integer input;
    
    // 主程式
    public static void main(String[] args) {
        
        Integer total = 0;  // 總金額

        do {
            showMenu(); // 印出主選單
            // 主選單項目
            switch (input) {
                case 0: 
                // 離開
                    break;
    
                case 1:
                case 2:
                case 3:
                // 商品
                    Integer number = 0; // 商品數量
                    // 數量輸入偵錯
                    do {
                        System.out.printf("請輸入數量: ");
                        if (scanner.hasNextInt()) {
                            number = scanner.nextInt();
                            if (number < 0) {
                                System.out.println("數量不得為負!");
                            }
                        } else {
                            System.out.println("輸入不得為非正整數!");
                            scanner.next();
                        }
                    } while (number < 0);
    
                    for (Drink drink: Drink.values()) {
                        if (input == drink.getDrinkCode()) {
                            total += number * drink.getDrinkPrice();
                            System.out.printf("已將 %d 杯%s加入購物車\n", number, drink.getDrinkName());
                        }
                    }
    
                    break;
    
                default:
                // 代號偵錯
                    if (input < 0){
                        System.out.println("輸入不得為負!");
                    } else {
                        System.out.println("無此選項!!");
                    }
            }
        } while (input!=0);
        
        System.out.printf("謝謝光臨，一共是 %d 元\n", total);

    }


    // 功能: 印出選單
    public static void showMenu() {

        System.out.println("******** 歡迎光臨 ********");
        System.out.println("編號       品名       價格");
        for (Drink drink: Drink.values()) {
            System.out.printf(" %-10s%-9s$ %d\n", 
                                    drink.getDrinkCode(), drink.getDrinkName(), drink.getDrinkPrice());
        }
        System.out.println("**************************");

        System.out.print("請輸入代號 (輸入 0 代表離開): ");
        // 代號偵錯(輸入是否為數字)
        if (scanner.hasNextInt()){
            input = scanner.nextInt();
        } else {
            scanner.next();
            System.out.printf("\n僅限輸入數字代號!!\n");
            DStore.showMenu();
        }
    }

}