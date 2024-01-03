package CompanySystem.Enum;
public enum Department {
    IT("IT"), 
    HR("HR"),
    MA("MA");

    public String name;
    private int num;

    Department(String name){
        this.name = name;
        this.num = 0;
    };

    public int getNum() {
        this.num ++;
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
