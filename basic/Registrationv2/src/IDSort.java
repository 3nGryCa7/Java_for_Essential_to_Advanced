package Registrationv2.src;

import java.util.Comparator;

public class IDSort implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(Integer.parseInt(s1.getID()), Integer.parseInt(s2.getID()));
    }
}
