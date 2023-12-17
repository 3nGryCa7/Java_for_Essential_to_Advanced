package Enum;

public enum Drink {
    BLACK_TEA(20,"紅茶"),
    GREEN_TEA(20,"綠茶"),
    MILK_TEA(25,"奶茶"),
    BUBBLE_TEA(35,"珍珠奶茶");

    private final int price;
    private final String name;
    /* 建構式 Start (5pt.) */
    private Drink(int price, String name) {
        this.price = price;
        this.name = name;
    }

    /* 建構式 End (5pt.) */

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

}
