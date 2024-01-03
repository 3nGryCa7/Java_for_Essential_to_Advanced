package CompanySystem.Enum;
public enum Department {
    IT("IT"), 
    HR("HR"),
    MA("MA");

    public String name;
    private int staff_num;
    private int manager_num;

    Department(String name){
        this.name = name;
        this.staff_num = 0;
        this.manager_num = 0;
    };

    public int getStaffNum() {
        this.staff_num ++;
        return this.staff_num;
    }

    public void setStaffNum(int num) {
        this.staff_num = num;
    }

    public int getManagerNum() {
        this.manager_num ++;
        return this.manager_num;
    }

    public void setManagerNum(int num) {
        this.manager_num = num;
    }
}