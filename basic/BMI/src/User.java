public class User {

    // 定義類別變數
    private String name;
    private float height;
    private int weight;
    private float BMI;

    public User(String name, float height, int weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    // 獲取名稱
    public String getName() {
        return this.name;
    }

    // 獲取身高
    public float getHeight() {
        return this.height;
    }

    // 獲取體重
    public int getWeight() {
        return this.weight;
    }

    // 設置名稱
    public void setName(String name) {
        this.name = name;
    }

    // 設置身高
    public void setHeight(float height) {
        this.height = height;
    }

    // 設置體重
    public void setWeight(int weight) {
        this.weight = weight;
    }

    // 計算BMI
    public void BMI_calculate() {
        this.BMI = this.weight / (float) Math.pow(this.height / 100, 2);
    }

    // 使用者資料輸出
    public String toString() {
        return String.format(
                "User name is: %s\n" +
                        "User weight is: %d kg\n" +
                        "User height is: %.1f cm\n" +
                        "User BMI is: %.2f\n" +
                        "--------------------",
                this.name, this.weight, this.height, this.BMI);
    }
}
