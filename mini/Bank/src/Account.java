public class Account {

    private String name; // 使用者名稱
    private String password; // 密碼
    private int balance; // 餘額

    /* 定義建構子 */
    public Account(String name, String password, int balance) {
        this.name = name;
        this.password = password;
        // 若為負數，則存款歸零
        if (ifNegative(balance)) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }

    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    // 存款功能
    public void deposit(int amount) {
        if (ifNegative(amount)) { // 檢測輸入值是否為負數
            System.out.println("You can't input negative number!");
        } else {
            this.balance += amount;
            System.out.println(this.name + " Deposit $" + amount + " Successfully.");
        }
    }

    // 提款功能
    public boolean withdraw(int amount) {
        if (ifNegative(amount)) { // 檢測輸入值是否為負數
            System.out.println("You can't input negative number!");
        } else if (this.balance < amount) { // 檢測存款是否足夠
            System.out.println("You don't have enough money.");
        } else {
            this.balance -= amount;
            System.out.println(this.name + " Withdraw $" + amount + " Successfully.");
            return true;
        }
        return false;
    }

    // 轉帳功能(收款人, 金額)
    public void transfer(Account payee, int amount) {
        if (this.withdraw(amount)) {
            payee.deposit(amount);
        }
    }

    // 密碼確認
    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    // 調閱使用者資料
    public void showData() {
        System.out.println("Name: " + this.name);
        System.out.println("Balance: " + this.balance);
    }

    // 檢測是否為負數
    public boolean ifNegative(int num) {
        return num <= 0;
    }
}
