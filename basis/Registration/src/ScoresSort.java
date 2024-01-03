import java.util.Comparator;

public class ScoresSort implements Comparator<Student> {

    @Override
    public int compare(Student var1, Student var2) {
        if (var1.getTotal().compareTo(var2.getTotal()) < 0) {
            return 1;
        } else if (var1.getTotal().compareTo(var2.getTotal()) > 0) {
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
