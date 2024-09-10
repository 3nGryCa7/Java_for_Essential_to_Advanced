import java.time.Year;

interface Accept {
    public boolean thisYear(Date date);
}

class DateFilter implements Accept {
    public boolean thisYear(Date date) {
         return date.getYear() == Year.now().getValue();
    }
}