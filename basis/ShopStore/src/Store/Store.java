package ShopStore.src.Store;

import java.util.Scanner;
import java.util.regex.Pattern;
import ShopStore.src.Enum.*;

public class Store {
    static Item[] cart = new Item[5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = 0;
        int cartSpace = 0;
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
                    if (cartSpace == 5) {
                        System.out.println("購物車已滿!"); continue;
                    }

                    Item item = new Item();
                    showMenu();
                    System.out.print("請選擇飲料編號：");
                    input = scanner.next();
                    if (isNumberOptionInRange(input, 1, 4)) {
                        Drink drink;
                        int i = 0;
                        for (Drink d: Drink.values()) {
                            if (input.equals(Integer.toString(i+1))) {
                                drink = d;
                                item.setDrink(drink);
                                break;
                            }
                            i++;
                        }
                    } else { continue; }
                    
                    item.setIce(selectIce(scanner));
                    item.setSugar(selectSugar(scanner));

                    while (true) {
                        System.out.print("\n請輸入數量：");
                        input = scanner.next();
                        if (!isNumberOptionInRange(input, 1)) { 
                        	continue;
                        }
                        item.setQuantity(Integer.parseInt(input));
                        break;
                    }

                    cart[cartSpace] = item;
                    System.out.printf("已將 %d 杯 %s 加入購物車\n", item.getQuantity(), item.getName());
                    total += item.getTotalPrice();
                    cartSpace ++;
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

    private static void showMenu() {
        System.out.println("\n＊＊＊＊＊＊＊＊ 菜單 ＊＊＊＊＊＊＊＊");
        System.out.printf("%-9s%-3s\t%s\n","編號","品名","價格");
        int i = 0;
        for (Drink drink: Drink.values()) {
            System.out.printf(" %-10d%-4s\t$%d\n", i+1, drink.getName(), drink.getPrice());
            i++;
        }
        System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
    }

    private static void showCart() {
        System.out.printf("%s\t\t%s\t\t%s\t\t%s\t\t%6s\t%5s\n", "品名", "冰量", "甜度", "數量", "單價", "總價");
        for (Item item: cart) {
        	if (item == null) { break; }
            System.out.print(item.toString());
        }
    }

    private static Ice selectIce(Scanner input) {
        while (true) {
            System.out.println("\n(1)正常冰 (2)少冰 (3)去冰");
            System.out.print("請選擇飲料冰量 [1,2,3]：");
            switch(input.next()) {
                case "1": return Ice.REGULAR;
                case "2": return Ice.LESS;
                case "3": return Ice.FREE;
                default: System.out.println("請輸入範圍內號碼!");
            }
        }
    }

    private static Sugar selectSugar(Scanner input) {
        while (true) {
            System.out.println("\n(1)正常糖 (2)少糖 (3)半糖 (4)微糖 (5)無糖");
            System.out.print("請選擇飲料甜度 [1,2,3,4,5]：");
            switch(input.next()) {
            case "1": return Sugar.REGULAR;
            case "2": return Sugar.LESS;
            case "3": return Sugar.HALF;
            case "4": return Sugar.QUARTER;
            case "5": return Sugar.FREE;
            default: System.out.println("請輸入範圍內號碼!");
        }
        }
    }

    public static boolean isNumberOptionInRange(String str, int min, int max) {
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

    public static boolean isNumberOptionInRange(String str, int min) {
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
