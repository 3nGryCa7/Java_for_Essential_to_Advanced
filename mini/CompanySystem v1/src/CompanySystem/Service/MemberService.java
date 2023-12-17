package CompanySystem.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import CompanySystem.Entity.GeneralStaff;
import CompanySystem.Enum.Department;

public class MemberService {

    static Scanner scanner = new Scanner(System.in);
    static File data = new File("data");

    public static void login() {
        String input, depart, id, passwd;

        try {
            System.out.print("請輸入工號(部門-號碼): ");
            input = scanner.nextLine();
            depart  = input.substring(0, 2);
            id = input.substring(3);
            boolean isDepart = depart.equals("HR") || depart.equals("IT") || depart.equals("MA");
            File folder = new File(data.getAbsolutePath() + "\\" + depart);
            boolean inDepart = Integer.parseInt(id) <= folder.listFiles().length;
            
            if (isDepart && inDepart) {
                    for (File file: folder.listFiles()) {
                        String filename = file.getName().substring(0, 6);
                        if (Integer.parseInt(id) == Integer.parseInt(filename)) {
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                            GeneralStaff staff = (GeneralStaff) ois.readObject();
                            ois.close();
    
                            System.out.print("輸入密碼: ");
                            passwd = scanner.nextLine();
                            if (passwd.equals(staff.getPasswd())) {
                                staff.showInfo();
                            } else {
                                System.out.println("密碼錯誤");
                            }
                        }
                    }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("查無此員工");
        }

    }

    public static void register() {
        String name, passwd;
        System.out.print("輸入姓名: ");
        name = scanner.nextLine();
        System.out.print("輸入密碼: ");
        passwd = scanner.nextLine();
        addToDepart(name, passwd);
        System.out.println("註冊成功!");
    }

    private static void addToDepart(String name, String passwd) {
        String input = "";
        System.out.println("1) 資訊部門");
        System.out.println("2) 人力資源");
        System.out.println("3) 行銷部門");
        do {
            System.out.print("選擇部門編號: ");
            input = scanner.nextLine();
        } while(!input.equals("1") && !input.equals("2") && !input.equals("3"));

        File DEP = null;
        Department depart = null;
        switch (input) {
        case "1":
            DEP = new File(data.getAbsolutePath() + "\\IT");
            depart = Department.IT;
            break;
        case "2":
            DEP = new File(data.getAbsolutePath() + "\\HR");
            depart = Department.HR;
            break;
        case "3":
            DEP = new File(data.getAbsolutePath() + "\\MA");
            depart = Department.MA;
            break;
        }
        try {
            String id = String.format("%06d", depart.getNum());
            GeneralStaff staff = new GeneralStaff(name, passwd, depart, id);
            File file = new File(DEP + "\\" + id + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(staff);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
