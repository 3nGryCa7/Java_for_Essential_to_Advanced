import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static String filename = "querylist.txt";    // 需使用內建環境執行 (button run/debug above entry point main())

    public static Hashtable<String, Integer> InitializeHB() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            List<Student> students = new ArrayList<>();
            Hashtable<String, Integer> list = new Hashtable<>();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.split(" ").length != 2) {
                    br.close();
                    throw new Exception("length of row data over 2!!! Accept (ID, school) only");
                }

                students.add(new Student(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));
            }
            students.sort(new IDSort());

            for (Student s : students) {
                list.put(s.getID(), s.getSchool());
            }

            br.close();
            return list;
        } catch (Exception e) {
            System.out.println("錯誤訊息: " + e.toString());
        }
        return new Hashtable<>();
    }

    public static void RunHashTable() {
        Hashtable<String, Integer> list = InitializeHB();
        Scanner scanner = new Scanner(System.in);
        String stuID;
        Integer school;
        String[] input;

        try {
            System.out.print("請輸入准考證號碼: ");
            if ((input = scanner.nextLine().split(" ")).length > 1) {
                throw new Exception("輸入錯誤，僅能輸入一個准考證號碼");
            } else if (list.get(input[0]) == null) {
                throw new Exception("准考證號碼未出現在榜單中耶");
            } else {
                stuID = input[0];
                school = list.get(stuID);
                switch (school) {
                    case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                        System.out.println(stuID + " is accpeted by school: " + Integer.toString(school));
                        break;
                    case -1:
                        System.out.println("未錄取QQ...");
                        break;
                    default:
                        System.out.println("學校編號不在預期範圍內喔~");
                }
            }

        } catch (Exception e) {
            System.out.println("Error occur: " + e.toString());
        } finally {
            System.out.println("查榜結束");
            scanner.close();
        }
    }

    public static List<Student> InitializeBS() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            List<Student> students = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                if (line.split(" ").length != 2) {
                    br.close();
                    throw new Exception("length of row data over 2!!! Accept (ID, school) only");
                }

                students.add(new Student(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));
            }
            Collections.sort(students, new IDSort());

            br.close();
            return students;
        } catch (Exception e) {
            System.out.println("錯誤訊息: " + e.toString());
        }
        return new ArrayList<>();
    }

    public static void RunBinarySearch() {
        List<Student> list = InitializeBS();
        Scanner scanner = new Scanner(System.in);
        String stuID;
        String[] input;

        try {
            System.out.print("請輸入准考證號碼: ");
            if ((input = scanner.nextLine().split(" ")).length > 1) {
                throw new Exception("輸入錯誤，僅能輸入一個准考證號碼");
                
            } else {
                stuID = input[0];
                int studentIdx = Collections.binarySearch(list, new Student(stuID), new IDSort());
                if (studentIdx >= 0) {
                    Student target = list.get(studentIdx);
                switch (target.getSchool()) {
                    case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10:
                        System.out.println(stuID + " is accpeted by school: " + Integer.toString(target.getSchool()));
                        break;
                    case -1:
                        System.out.println("未錄取QQ...");
                        break;
                    default:
                        System.out.println("學校編號不在預期範圍內喔~");
                }
                } else {
                    throw new Exception("准考證號碼未出現在榜單中耶");
                }
            }
        } catch (Exception e) {
            System.out.println("Error occur: " + e.toString());
        } finally {
            System.out.println("查榜結束");
            scanner.close();
        }
    }

    public static void main(String[] args) {
        RunBinarySearch();
    }
}
