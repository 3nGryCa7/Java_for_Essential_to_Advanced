package Employee.src;

public class Guide extends Employee implements Volunteer {

    public Guide(String name) {
        super(name);
    }

    @Override
    public String getIntroduction() {
        return "大廳服務引導員";
    }

    @Override
    public void issueCertificate() {
        System.out.println("給予感謝狀 : 乙張");
    }

}