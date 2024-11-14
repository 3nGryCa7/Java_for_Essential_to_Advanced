
enum Sex {
	MALE, FEMALE
}
enum BloodType {
	A, B, AB, O
}

public class Member {
	String name;
	String phoneString;
	Date birthday;
	Sex sex;
	BloodType bloodType;
	boolean vip;

	public Member(String n, String p, Date d, Sex s, BloodType t, boolean v) {
		name = n;
		phoneString = p;
		birthday = d;
		sex = s;
		bloodType = t;
		vip = v;
	}

	public void setPhoneString(String phs) {
		phoneString = phs;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date d) {
		birthday = d;
	}
	public BloodType getBloodType() {
		return bloodType;
	}

	public String toString () {
            // return "Member:"+name+" "+phoneString;
		String vipinfo = vip ? "VIP":"";
		return name+": " +birthday+" "+sex+" "+bloodType+" "+phoneString+" "+vipinfo;
	}
}
