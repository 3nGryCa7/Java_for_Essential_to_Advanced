package ShopStore.src.Store;
import ShopStore.src.Enum.*;
public class Item {
    private Drink drink;
    private int quantity;
    private Ice ice;
    private Sugar sugar;

    public Item(Drink drink, int quantity, Ice ice, Sugar sugar) {
        this.drink = drink;
        this.quantity = quantity;
        this.ice = ice;
        this.sugar = sugar;
    }

    public Item() { }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setIce(Ice ice) {
        this.ice = ice;
    }

    public void setSugar(Sugar sugar) {
        this.sugar = sugar;
    }

    public String getName() {
        return this.drink.getName();
    }
    public int getQuantity() {
        return this.quantity;
    }
    public int getPrice() {
        return this.drink.getPrice() ;
    }
    public int getTotalPrice() {
        return this.getPrice() * this.getQuantity();
    }

    @Override
    public String toString() {
        return String.format(
                "%-4s\t\t%-3s\t\t%-3s\t\t%3s\t\t$%6s\t\t$%6s\n", /* 顯示商品資訊 (5pt.) */
                this.getName(), this.ice.getDescription(), this.sugar.getDescription(), this.quantity, 
                this.getPrice(), this.getTotalPrice());
    }
}
