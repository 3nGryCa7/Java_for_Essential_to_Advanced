package Registration.src;

import java.util.Arrays;

public class Student {
    private String Id;
    private Integer chinese;
    private Integer english;
    private Integer math;
    private String[] wishes;
    private String school;

    public Student() {}

    public Student(String Id, Integer chinese, Integer english, Integer math, String[] wishes) {
        this.Id = Id;
        this.chinese = chinese;
        this.english = english;
        this.math = math;
        this.wishes = wishes;
        this.school = null;
    }

    /* getter */
    public String getId() { return this.Id; }
    public Integer getChinese() { return this.chinese; }
    public Integer getEnglish() { return this.english; }
    public Integer getMath() { return this.math; }
    public String[] getWishes() { return this.wishes; }
    public String getSchool() { return this.school != null ? this.school : "沒有學校"; }

    /* setter */
    public void setId(String Id) { this.Id = Id; }
    public void setChinese(Integer Chinese) { this.chinese = Chinese; }
    public void setEnglish(Integer English) { this.english = English; }
    public void setMath(Integer Math) { this.math = Math; }
    public void setSchool(String School) { this.school = School; }

    /* others */
    public Integer getTotal() { return this.chinese + this.english + this.math; }

    @Override
    public String toString() {
        return new String(
            "Student ID: " + getId() +
            ", Chinese: " + Integer.toString(getChinese()) + 
            ", English: " + Integer.toString(getEnglish()) +
            ", Math: " + Integer.toString(getMath()) +
            ", Wishes: " + Arrays.toString(wishes)
        );
    }
}
