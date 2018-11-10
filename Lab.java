import java.util.ArrayList;

// class Lab:
// 	Date of Lab
//  TAs Present (A list/string comprising of the names of the TAs present during the lab)
// 	Min. Lab Duration (The min time for which the student has to be present in the lab to be marked present)
// 	Student[n] (An array or list where ‘n’ is the no. of students in the class) and Student[i] would be referencing to an object of a new subclass comprising of:
// 		Roll No (UNIQUE ID)
// 		Login Time
// 		Logout Time


class Lab {
    Date date;
    ArrayList<String> TAs;
    int minLabDuration; // in hours
    ArrayList<StudentInLab> studentsInLab; // the new student class for the Lab class

    public Lab(int day, int month, int year) {
        this.date = new Date(day, month, year);
        studentsInLab = new ArrayList<StudentInLab>();
    }

    public void AddTA(String name) {
        this.TAs.add(name);
    }

    public addStudent(int rollNo, String id, Time in, Time out) {
        studentsInLab.add(new StudentInLab(rollNo, id, in, out));
    }
}

public class StudentInLab {
    int rollNo;
    String id;
    Time inTime;
    Time outTime;
    Student studentData;

    public StudentInLab(int rollNo, String id, Time inTime, Time outTime) {
        this.rollNo = roll.no;
        this.id = id;
        this.inTime = inTime;
        this.outTime = outTime;
    }
}

public class Date {
    int d;
    int m;
    int y;
    
    public Date(int day, int month, int year) {
        this.d = day;
        this.m = month;
        this.y = year;
    }
}

public class Time {
    int h;
    int m;
    int s;
    
    public Time(int hours, int minutes, int seconds) {
        this.h = hours;
        this.m = minutes;
        this.s = seconds;
    }
}
