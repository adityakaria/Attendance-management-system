import java.util.Scanner;

public class Batch {
    // class Identification Parameters
    int classStrength;
    String branch;
    int Gradyear;
    Student[] students;

    public Batch(String branch, int year, int Strength) {
        this.classStrength = Strength;
        this.branch = branch;
        this.Gradyear = year;
        this.students = new Student[this.classStrength];
    }

    // Add details of all students of the class based on the class Strength
    public void setStudentDetails() {
        System.out.println("Please Enter the student details...(tab separated)");
        System.out.println("RollNo  IDnumber    Name    ContactNo   EmailID");

        for (int i = 0; i < this.classStrength; i++) {
            Scanner in = new Scanner(System.in);
            String[] info = in.nextLine().split(" ");
            students[i] = new Student(Integer.parseInt(info[0]), info[1], info[2], Long.parseLong(info[3]), info[4]);
        }

        System.out.println("");
    }

    // default printable method
    public String toString() {
        String info = "\n";
        System.out.println("RollNo  IDnumber    Name    contactNo   emailID");
        
        for (int i = 0; i < this.classStrength; i++) {
            info += (String.valueOf(students[i].rollNo) + "    " + students[i].id + "   " + students[i].name + "    " + String.valueOf(students[i].getContactNumber()) + "   " + students[i].getEmailID() + "\n");
        }
        return info;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Please enter the following details: (tab separated)");
        System.out.println("Batch department    year    Class Strength");
        String[] batch = in.nextLine().split(" ");
        Batch b1 = new Batch(batch[0], Integer.parseInt(batch[1]), Integer.parseInt(batch[2]));
        
        b1.setStudentDetails();
        System.out.println(b1);
    }
}

// 1 17IT201 abc 1092348798 khfas@jaldsf.co
// 2 7ybad adusih 3128423412 sjdkahf@asfidh.com
// 3 fh9dj ajdhfa 9812749380 adusfi@daf.com
// 4 aip3 ishdfa 9108473138 akhsf@adf.com