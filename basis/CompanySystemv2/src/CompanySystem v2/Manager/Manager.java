package CompanySystem.Manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import CompanySystem.Entity.GeneralStaff;
import CompanySystem.Enum.Department;

public class Manager extends GeneralStaff {
    
    public Manager(String name, String passwd, Department depart, String id) {
        super(name, passwd, depart, id);
    }

    public void showStaffs(File folder) {
        System.out.println(this.getDepart().name + "部門名單 : ");
        try {
            for (File file: folder.listFiles()) {
                Boolean isStaff = (file.getName().charAt(0) == '0');
                String prefix = isStaff ? "一般員工 : " : "主管 : ";
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                GeneralStaff staff = (GeneralStaff) ois.readObject();
                System.out.println(prefix + staff.getName());
                ois.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
