package CompanySystem.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;
import CompanySystem.Entity.GeneralStaff;
import CompanySystem.Enum.Department;
import CompanySystem.Manager.Manager;

public class MemberService {

    static Scanner scanner = new Scanner(System.in);
    static File data = new File("data");

    public static void login() {
        String input, depart, id;

        try {
            System.out.print("請輸入工號(部門-號碼): ");
            input = scanner.nextLine();
            depart  = input.substring(0, 2);
            id = input.substring(3);
            boolean isDepart = depart.equals("HR") || depart.equals("IT") || depart.equals("MA");
            File folder = new File(data.getAbsolutePath() + "\\" + depart);
            int inDepart = Arrays.binarySearch(folder.list(), id+".ser");
            
            if (isDepart && (inDepart >= 0)) {
                    for (File file: folder.listFiles()) {
                        String filename = file.getName().substring(0, 6);
                        if (Integer.parseInt(id) == Integer.parseInt(filename)) {
                            boolean isManager = (id.charAt(0) == '1');
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                            if (isManager) {
                                Manager manager = (Manager) ois.readObject();
                                if (validate(manager)) {
                                    manager.showInfo();
                                    manager.showStaffs(folder);
                                } else {
                                    System.out.println("密碼錯誤");
                                }
                            } else {
                                GeneralStaff staff = (GeneralStaff) ois.readObject();
                                if (validate(staff)) {
                                    staff.showInfo();
                                } else {
                                    System.out.println("密碼錯誤");
                                }
                            }
                            ois.close();
                            break;
                        }
                    }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("查無此員工");
        }
    }

    public static boolean validate(GeneralStaff person) {
        System.out.print("輸入密碼: ");
        String passwd = scanner.nextLine();
        if (passwd.equals(person.getPasswd())) {
            return true;
        } else {
            return false;
        }
    }

    public static void register() {
        String name, passwd;
        System.out.print("輸入姓名: ");
        name = scanner.nextLine();
        System.out.print("輸入密碼: ");
        passwd = scanner.nextLine();
        addToDepart(name, passwd);
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
            String id = null;
            System.out.print("是否為管理人員 [Y/n]: ");
            String isManager = scanner.nextLine();

            if (isManager.equals("Y")) {
                id = String.format("1%05d", depart.getManagerNum());
                File file = new File(DEP + "\\" + id + ".ser");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                Manager manager = new Manager(name, passwd, depart, id);
                oos.writeObject(manager);
                oos.close();
            } else if (isManager.equals("n")) {
                id = String.format("%06d", depart.getStaffNum());
                File file = new File(DEP + "\\" + id + ".ser");
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                GeneralStaff staff = new GeneralStaff(name, passwd, depart, id);
                oos.writeObject(staff);
                oos.close();
            } else {
                throw new Exception("You can only input [Y/n]!");
            }
            System.out.println("註冊成功!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
