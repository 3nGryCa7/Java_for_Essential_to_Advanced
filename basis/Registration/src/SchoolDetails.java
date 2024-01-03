import java.util.ArrayList;

class SchoolDetails {
    private Integer spot;
    private ArrayList<String> studentList;

    public SchoolDetails(Integer spot, ArrayList<String> studentList2) {
        this.spot = spot;
        this.studentList = studentList2;
    }

    /* getter */
    public Integer getSpot() { return this.spot; }
    public ArrayList<String> getStudentList() { return this.studentList; }
    /* setter */
    public void setSpot(Integer spot) { this.spot = spot; }
    public void setStudentList(String student) { this.studentList.add(student); }

    /* others */
    public void decreaseSpot() { this.spot--; }
}