package DrinkStore;

public enum Drink {
    BLACK_TEA(1, 20, "紅茶"),
    GREEN_TEA(2, 20, "綠茶"),
    MILK_TEA(3, 25, "奶茶");

    // 物件屬性
    private final Integer drinkCode;
    private final Integer drinkPrice;
    private final String drinkName;

    // 建構式
    private Drink(Integer code, Integer price, String product) {
        drinkCode = code;
        drinkPrice = price;
        drinkName = product;
    }

    // 取得飲料代碼
    public Integer getDrinkCode(){
        return drinkCode;
    }

    // 取得飲料價格
    public Integer getDrinkPrice(){
        return drinkPrice;
    }

    // 取得飲料名稱
    public String getDrinkName(){
        return drinkName;
    }
}
