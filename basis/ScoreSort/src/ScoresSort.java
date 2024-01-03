/*
 * 假定scores.txt檔案為500位同學的考試成績,每行資料依序為學號,國文,英文,數學.

    1. 請設計資料結構可載入所有同學成績,計算總分並列印.

    2. 請將所有同學依(a)總分、(b)國文、(c)英文、(d)數學進行排序, 印出排序結果.(若總分一樣則先比較國文成績，國文成績又一樣則再比較英文成績，英文成績又一樣最後才比較數學成績，所以只會有一種output，而不是分別用總分、國文、英文、數學排)


 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoresSort implements Comparator<Student> {
    public static void main(String[] args) throws Exception {

        List<Student> students = new ArrayList<Student>();

        try (BufferedReader br = new BufferedReader(new FileReader("../scores.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Student student = new Student(
                    line.split(",")[0], 
                    Integer.parseInt(line.split(",")[1]),
                    Integer.parseInt(line.split(",")[2]),
                    Integer.parseInt(line.split(",")[3])
                    );
                // System.out.println(student.toString());
                students.add(student);
            }

            ScoresSort comparator =  new ScoresSort();
            students.sort(comparator);

            for (Student s : students) {
                System.out.println("總分: " + Integer.toString(s.getAmount()) + ", " +  s.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    @Override
    public int compare(Student var1, Student var2) {
        if (var1.getAmount().compareTo(var2.getAmount()) < 0) {
            return 1;
        } else if (var1.getAmount().compareTo(var2.getAmount()) > 0) {
            return -1;
        } else if (var1.getChinese().compareTo(var2.getChinese()) < 0) {
            return 1;
        } else if (var1.getChinese().compareTo(var2.getChinese()) > 0) {
            return -1;
        } else if (var1.getEnglish().compareTo(var2.getEnglish()) < 0) {
            return 1;
        } else if (var1.getEnglish().compareTo(var2.getEnglish()) > 0) {
            return -1;
        } else if (var1.getMath().compareTo(var2.getMath()) == 0) {
            return 0;
        } else {
            return var1.getMath().compareTo(var2.getMath()) > 0 ? -1 : 1;
        }
    }
    
}
