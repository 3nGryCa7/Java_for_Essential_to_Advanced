import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Date d1 = new Date(1988, 3, 10);

        Member m1 = new Member("Steph Liu", "091919191", d1, Sex.MALE, BloodType.O, false);
        System.out.println("m1 is " + m1);
        m1.setPhoneString("0923232323");
        System.out.println("m1 now becomes " + m1);

        Member m2 = new Member("Jennifer Chen", "092919191", new Date(2001, 5,7), Sex.FEMALE, BloodType.B, true);
        System.out.println("m2 is " + m2);

        // Test
        Member m3 = new Member("Sam John", "0928183023", new Date(2024, 5, 13), Sex.MALE, BloodType.B, true);

        Member[] members = {m1, m2, m3};

        Member[] members2 = thisYearMembers(members, new DateFilter());
        System.out.println("This year members count: " + members2.length);
    }

    static Member[] thisYearMembers(Member[] members, Accept accept) {
        int count = 0;
        for (Member j : members) {
            if (accept.thisYear(j.getBirthday())) count++;
        }
        Member[] result = new Member[count];
        int index =0;
        for (Member j : members) {
            if (accept.thisYear(j.getBirthday())) result[index++] = j;
        }
        return result;
    }
}