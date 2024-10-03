package CompanySystemv2.src.Company;
import java.io.File;
import java.util.Scanner;

import CompanySystemv2.src.Enum.Department;
import CompanySystemv2.src.Service.MemberService;

public class CompanySystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        
        String input = "";

        // 初始化資料夾(建立data資料夾)
        initialize();
        
        while(!input.equals("0")) {
            showMenu();
            System.out.print("請輸入您的選擇 [1,2,0]: ");
            input = scanner.nextLine();

            switch (input) {
            case "1":
                MemberService.login();
                break;
            case "2":
                MemberService.register();
                break;
            case "0":
                break;
            default:
                System.out.println("無此選項!");
            }
        }

    }

    public static void initialize() {
        File data = new File("data");
        if (!data.exists() && !data.isDirectory()) {
            data.mkdir();
        }
        File HR = new File(data.getAbsolutePath() + "\\HR");
        if (!HR.exists() && !HR.isDirectory()) {
            HR.mkdir();
        } else {
            int m_num = 0, s_num = 0;
            for (File file: HR.listFiles()) {
                if (file.getName().charAt(0) == '1') {
                    m_num ++;
                } else {
                    s_num ++;
                }
            }
            Department.HR.setManagerNum(m_num);
            Department.HR.setStaffNum(s_num);
            // Department.HR.setNum(HR.listFiles().length);
        }
        File IT = new File(data.getAbsolutePath() + "\\IT");
        if (!IT.exists() && !IT.isDirectory()) {
            IT.mkdir();
        } else {
            int m_num = 0, s_num = 0;
            for (File file: IT.listFiles()) {
                if (file.getName().charAt(0) == '1') {
                    m_num ++;
                } else {
                    s_num ++;
                }
            }
            Department.IT.setManagerNum(m_num);
            Department.IT.setStaffNum(s_num);
            // Department.IT.setNum(IT.listFiles().length);
        }
        File MA = new File(data.getAbsolutePath() + "\\MA");
        if (!MA.exists() && !MA.isDirectory()) {
            MA.mkdir();
        } else {
            int m_num = 0, s_num = 0;
            for (File file: MA.listFiles()) {
                if (file.getName().charAt(0) == '1') {
                    m_num ++;
                } else {
                    s_num ++;
                }
            }
            Department.MA.setManagerNum(m_num);
            Department.MA.setStaffNum(s_num);
            // Department.MA.setNum(MA.listFiles().length);
        }
    }

    public static void showMenu() {
        System.out.println("****歡迎進入公司系統****");
        System.out.println("1) 登入");
        System.out.println("2) 註冊");
        System.out.println("0) 離開");
    }

    

}
