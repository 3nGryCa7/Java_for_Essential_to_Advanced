package Registrationv2.src;

public class Student {
    private String ID;
    private Integer school;

    public Student(String id) { this.ID = id; }
    public Student(String id, Integer school) {
        this.ID = id;
        this.school = school;
    }

    /* getter */
    public String getID() { return this.ID; }
    public Integer getSchool() { return this.school; }
    /* setter */
    public void setID(String ID) { this.ID = ID; }
    public void setSchool(Integer school) { this.school = school; }
}
