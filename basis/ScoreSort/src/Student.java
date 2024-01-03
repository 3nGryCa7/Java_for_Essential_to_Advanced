public class Student {
    private String Id;
    private Integer Chinese;
    private Integer English;
    private Integer Math;

    public Student() {}

    public Student(String Id, Integer Chinese, Integer English, Integer Math) {
        this.Id = Id;
        this.Chinese = Chinese;
        this.English = English;
        this.Math = Math;
    }

    public String getId() {
        return this.Id;
    }

    public Integer getChinese() {
        return this.Chinese;
    }

    public Integer getEnglish() {
        return this.English;
    }

    public Integer getMath() {
        return this.Math;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setChinese(Integer Chinese) {
        this.Chinese = Chinese;
    }

    public void setEnglish(Integer English) {
        this.English = English;
    }

    public void setMath(Integer Math) {
        this.Math = Math;
    }

    public Integer getAmount() {
        return this.Chinese + this.English + this.Math;
    }

    @Override
    public String toString() {
        return new String(
            getId() +
            ", chinese: " + Integer.toString(getChinese()) + 
            ", english: " + Integer.toString(getEnglish()) +
            ", math: " + Integer.toString(getMath())
            );
    }
}
