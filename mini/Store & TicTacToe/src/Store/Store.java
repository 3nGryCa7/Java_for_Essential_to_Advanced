package Store;

import java.util.Scanner;
import java.util.regex.Pattern;
import Enum.*;

public class Store {
    // 5 個空位的購物車
    static Item[] cart = new Item[5];

    /*  40pt.  */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0;
        int cartSpace = 0/* 初始的購物車空間/容量，不可寫死 5 (5pt.) */;
        while (true) {
            System.out.println("\n＊＊＊＊＊＊＊＊ 歡迎來到大台科飲料店 ＊＊＊＊＊＊＊＊");
            System.out.println("(1) 點餐\n(2) 查看購物車\n(0) 結帳並退出系統");
            System.out.print("請選擇功能 [0,1,2]：");

            String input = scanner.next();
            if (input.equals("0")) { break; }
            if (!isNumberOptionInRange(input, 0, 2)) continue;

            int selected = Integer.parseInt(input);
            switch (selected) {
                case 1:
                    /* 如果購物車滿了就跳出去 Start (5pt., 3) */
                    if (cartSpace == 5) {
                        System.out.println("購物車已滿!"); continue;
                    }
                    /* 如果購物車滿了就跳出去 End (5pt.) */

                    Item item = new Item();
                    // 1. 選擇飲料編號
                    showMenu();
                    System.out.print("請選擇飲料編號：");
                    input = scanner.next();
                    /* 如果飲料不在選項之中 (5pt.) */
                    if (isNumberOptionInRange(input, 1, 4)) {
                        Drink drink;
                        int i = 0;
                        for (Drink d: Drink.values()) {
                            if (input.equals(Integer.toString(i+1))) {
                                drink = d;
                                item.setDrink(drink /* 設定飲料 5pt. */);
                                break;
                            }
                            i++;
                        }
                    } else { continue; }
                    
                    // 2. 選擇飲料冰塊
                    item.setIce(selectIce(scanner));
                    // 3. 選擇飲料甜度
                    item.setSugar(selectSugar(scanner));

                    // 4. 選擇飲料數量
                    while (true) {
                        System.out.print("\n請輸入數量：");
                        input = scanner.next();
                        /* 如果數量不合理，跳回選單 (5pt.) */
                        if (!isNumberOptionInRange(input, 1)) { 
                        	continue;
                        }
                        item.setQuantity(Integer.parseInt(input));
                        break;
                    }

                    /* 把 item 物件存入 cart 陣列中 (5pt.) */
                    cart[cartSpace] = item;
                    System.out.printf("已將 %d 杯 %s 加入購物車\n", item.getQuantity(), item.getName());
                    /* 計算目前購物車的總價格 (total)、適當調整 cartSpace 變數 Start (10pt., 2)  */
                    total += item.getTotalPrice();
                    cartSpace ++;
                    /* 計算目前購物車的總價格 (total)、適當調整 cartSpace 變數 End (10pt.)  */
                    break;
                case 2:
                    showCart();
                    break;
                default:
                    System.out.println("無此選項！！\n");
                    scanner.nextLine();
            }
        };
        System.out.printf("\n\n謝謝光臨，一共是：%d 元\n", total);
    }

    /* 5pt. */
    private static void showMenu() {
        System.out.println("\n＊＊＊＊＊＊＊＊ 菜單 ＊＊＊＊＊＊＊＊");
        System.out.printf("%-9s%-3s\t%s\n","編號","品名","價格");
        /* 顯示菜單內容 Start (5pt., 3)  */
        int i = 0;
        for (Drink drink: Drink.values()) {
            System.out.printf(" %-10d%-4s\t$%d\n", i+1, drink.getName(), drink.getPrice());
            i++;
        }
        /* 顯示菜單內容 End (5pt.)  */
        System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
    }

    /* 5pt. + 5pt. */
    private static void showCart() {
        // 標題
        System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%6s\t%5s\n", "品名", "冰量", "甜度", "數量", "單價", "總價");
        // 購物車內容
        /* 顯示購物車內容 Start (5pt. + 5pt.若有考慮效能 & item 的函式作列印, 3)  */
        for (Item item: cart) {
        	if (item == null) { break; }
            System.out.print(item.toString());
        }
        /* 顯示購物車內容 End (5pt. + 5pt.)  */
    }

    /* 5pt. */
    private static Ice selectIce(Scanner input) {
        while (true) {
            System.out.println("\n(1)正常冰 (2)少冰 (3)去冰");
            System.out.print("請選擇飲料冰量 [1,2,3]：");
            /* Switch-case 處理輸入的冰量，輸入錯誤要請使用者重新輸入 Start (5pt., 6)  */
            switch(input.next()) {
                case "1": return Ice.REGULAR;
                case "2": return Ice.LESS;
                case "3": return Ice.FREE;
                default: System.out.println("請輸入範圍內號碼!");
            }
            /* Switch-case 處理輸入的冰量，輸入錯誤要請使用者重新輸入 End (5pt.)  */
        }
    }

    /* 5 pt. */
    private static Sugar selectSugar(Scanner input) {
        while (true) {
            System.out.println("\n(1)正常糖 (2)少糖 (3)半糖 (4)微糖 (5)無糖");
            System.out.print("請選擇飲料甜度 [1,2,3,4,5]：");
            /* Switch-case 處理輸入的甜度，輸入錯誤要請使用者重新輸入 Start (5pt., 8)  */
            switch(input.next()) {
            case "1": return Sugar.REGULAR;
            case "2": return Sugar.LESS;
            case "3": return Sugar.HALF;
            case "4": return Sugar.QUARTER;
            case "5": return Sugar.FREE;
            default: System.out.println("請輸入範圍內號碼!");
        }
            /* Switch-case 處理輸入的甜度 End (5pt.)  */
        }
    }

    // 判斷輸入的文字是否在選項範圍內，有最大最小值
    public static boolean isNumberOptionInRange(String str, int min, int max) {
        // 採用正則表示式的方式來判斷一個字串是否為數字，這種方式判斷面比較全
        boolean isNumber = Pattern.compile("^-?\\d*$").matcher(str).find();
        if (!isNumber) {
            System.out.println("僅限輸入數字！\n");
            return false;
        }
        if (Integer.parseInt(str) < min || Integer.parseInt(str) > max) {
            System.out.println("輸入錯誤！");
            return false;
        }
        return true;
    }

    // 判斷輸入的文字是否在選項範圍內，只限制最小值
    public static boolean isNumberOptionInRange(String str, int min) {
        // 採用正則表示式的方式來判斷一個字串是否為數字，這種方式判斷面比較全
        boolean isNumber = Pattern.compile("^-?\\d*$").matcher(str).find();
        if (!isNumber) {
            System.out.println("僅限輸入數字！\n");
            return false;
        }
        if (Integer.parseInt(str) < min) {
            System.out.println("\n輸入錯誤！\n");
            return false;
        }
        return true;
    }
}
