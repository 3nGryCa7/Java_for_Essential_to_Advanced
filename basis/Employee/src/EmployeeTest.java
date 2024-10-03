package Employee.src;

public class EmployeeTest {

    public EmployeeTest() {}

    public static void main(String[] args) {
        Engineer engineer = new Engineer("張明倫");
        Guide guide = new Guide("林阿滿");

        System.out.println("工程師 - " + engineer.getIntroduction());
        System.out.println("員工姓名 : \n      " + engineer.getName());
        System.out.println("支付約定 :");
        System.out.print("     ");
        engineer.paySalary();

        System.out.println();

        System.out.println("引導員 - " + guide.getIntroduction());
        System.out.println("員工姓名 : \n      " + guide.getName());
        System.out.println("支付約定 :");
        System.out.print("     ");
        guide.issueCertificate();
    }
}