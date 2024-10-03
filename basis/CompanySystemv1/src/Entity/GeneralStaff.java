package CompanySystemv1.src.Entity;

import java.io.Serializable;

import CompanySystemv1.src.Enum.Department;

public class GeneralStaff implements Serializable{
    private static final long serialVersionUID = 1588878192777294277L;

    private String name;
    private String passwd;
    private Department depart;
    private String id;

    public GeneralStaff(){};
    
    public GeneralStaff(String name, String passwd, Department depart, String id) {
        this.name = name;
        this.passwd = passwd;
        this.depart = depart;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public Department getDepart() {
        return this.depart;
    }

    public String getID() {
        return this.id;
    }


    public void showInfo() {
        System.out.println("姓名: " + this.name + "\n部門: " + this.depart.name + "\n工號: " + String.format("%06d", Long.parseLong(this.id))); 
    }
}
