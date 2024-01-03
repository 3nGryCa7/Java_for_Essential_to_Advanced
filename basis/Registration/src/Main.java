import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Student> students = new ArrayList<Student>();

        try {

            /* 讀取 學生成績、志願序 */
            BufferedReader brScores = new BufferedReader(new FileReader("../scores.txt"));
            BufferedReader brWishes = new BufferedReader(new FileReader("../wish.txt"));
            String score, wish;

            while ((score = brScores.readLine()) != null && (wish = brWishes.readLine()) != null) {
                // wish.txt 檔案格式: index[0]=學號, index[-1]=0 (我不知道為什麼，但這個世界就是這麼可怕)
                String[] wishes = Arrays.copyOfRange(wish.split(" "), 1, wish.split(" ").length - 1);

                Student student = new Student(
                        score.split(",")[0],
                        Integer.parseInt(score.split(",")[1]),
                        Integer.parseInt(score.split(",")[2]),
                        Integer.parseInt(score.split(",")[3]),
                        wishes);
                // System.out.println(student.toString());
                students.add(student);
            }
            brScores.close();
            brWishes.close();

            /* 讀取 學校招生名單 */
            Map<Integer, SchoolDetails> schools = new Hashtable<>();
            BufferedReader brSchools = new BufferedReader(new FileReader("../school.txt"));
            String school;
            while ((school = brSchools.readLine()) != null) {
                SchoolDetails details = new SchoolDetails(Integer.parseInt(school.split(" ")[1]),
                        new ArrayList<String>());
                schools.put(Integer.parseInt(school.split(" ")[0]), details);
            }
            brSchools.close();

            /* 成績排序 */
            ScoresSort comparator = new ScoresSort();
            students.sort(comparator);

            // System.out.println("=== 成績排序結果 ===");
            for (Student s : students) {
                // System.out.println("Total Score: " + Integer.toString(s.getTotal()) + ", " + s.toString());

                for (String w : s.getWishes()) {
                    SchoolDetails ws = schools.get(Integer.parseInt(w));
                    if (ws.getSpot() != 0) {
                        ws.decreaseSpot();
                        ws.setStudentList(s.getId());
                        s.setSchool(w);
                        break;
                    }
                }
            }
            System.out.println("=== 學校分發結果 ===");
            schools.forEach( (sId, details) -> {
                System.out.println("學校編號: " + Integer.toString(sId) + "\n錄取學生名單: " + details.getStudentList().toString());
            });

            System.out.println("=== 分發落榜名單 ===");
            List<String> noSchool = new ArrayList<>();
            for (Student s: students) {
                if (s.getSchool() == "沒有學校") {
                    noSchool.add(s.getId());
                }
            }
            System.out.println(noSchool.toString());

            System.out.println("=== 考生總分/錄取學校 ===");
            for (Student s: students) {
                System.out.println("學生編號: " + s.getId() + ", 總分: " + Integer.toString(s.getTotal()) + ", 錄取學校: " + s.getSchool());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
