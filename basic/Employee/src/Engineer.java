package Employee.src;

public class Engineer extends Employee implements Salaried {

    public Engineer(String name) {
        super(name);
    }

    @Override
    public String getIntroduction() {
        return "公司網路管理工程師";
    }

    @Override
    public void paySalary() {
        System.out.println("給予薪資 : 35000 元");
    }

}