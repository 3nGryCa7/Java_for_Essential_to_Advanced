public class Date {
    public static final Date MILLENIUM = new Date(2000, 1, 1);
    int year;
    int month;
    int day;
    static int numOfDateObjects = 0;
    public Date() {
      this(1, 1, 1);
    }
    public Date(int yy, int mm) {
			this(yy, mm, 1);
    }
    public Date(int year, int month, int day) {
        // numOfDateObjects++;
				if(year > 0) this.year = year;
				else this.year = 1;
        if(month >= 1 && month <=12) this.month = month;
        else this.month = 1;
        if(day >= 1 && day <=31) this.day = day;
        else this.day = 1;
        // System.out.println("numOfDateObjects "+numOfDateObjects);
    }
    public int getYear() {
       return year;
    }
    public void setYear(int yy) {
        if(yy <= 0) return;
       year = yy;
    }
    public int getMonth() {
       return month;
    }
    public void setMonth(int mm) {
        if(mm <= 0) return;
       month = mm;
    }
    public int getDay() {
       return day;
    }
    public void setDay(int dd) {
        if(dd <= 0) return;
       day = dd;
    }
    public boolean isFirstDayOfYear() {
        if(month == 1 && day == 1) return true;
        else return false;
    }
    public boolean equals(Object o) {
        Date other = (Date) o;
        // if(this.year == other.year && this.month == other.month && this.day == other.day) return true;
        // else return false;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }
    public String toString() {
      // return "Date:"+month+"/"+day;
      // System.out.println("this " + month+" "+day);
      return String.format("Date: %d/%02d/%02d",year, month,day);
    }
}
