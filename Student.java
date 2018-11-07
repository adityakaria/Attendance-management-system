public class Student {
    public String id; // eg: 17IT203
    public int rollNo; //eg: 25
    public String name;
    /* Note: Below parameters are made private to student contact privacy, and to avoid unauthorised manipulation. Although, they are accessible with get and set methods in the class */
    private long contactNo;
    private String emailID;
    private int labsAttended;
    private int totalLabs;
    private float attendance;
    
    public Student(int roll_number, String student_id_number, String student_name, long contact_number , String email_ID) {
        this.rollNo = roll_number;
        this.id = student_id_number;
        this.name = student_name;
        this.contactNo = contact_number;
        this.emailID = email_ID;
        this.labsAttended = 0;
        this.totalLabs = 0;
        this.attendance = 0;
    }

    // for viewing editing contact number
    public long getContactNumber() { 
        return this.contactNo;
    }
    public void setContactNumber(long contact_number) {
        this.contactNo = contact_number;
    }

    //for viewing and editing emailID
    public String getEmailID() { 
        return this.emailID;
    }
    public void setEmailID(String email_id) {
        this.emailID = email_id;
    }

    //for viewing student attendance
    public float getAttendance() {
        if (this.totalLabs == 0) {
            return 0;
        }
        return this.attendance;
    }

    // Called when student present
    public float present() {
        this.totalLabs++;
        this.labsAttended++;
        this.attendance = (float)labsAttended / totalLabs;
        return this.attendance;
    }
    public float absent() {
        this.totalLabs++;
        this.attendance = (float)labsAttended / totalLabs;
        return this.attendance;
    }

    // To check attendance
    public void CheckAttendance() {
        if (this.attendance < 75) {
            System.out.println("WARNING: " + this.name + " has attendance shortage at " + String.valueOf(this.attendance));
            // In case we require:
            // return 1; 
        }
        else {
            System.out.println(this.name + " has sufficient attendance at " + String.valueOf(this.attendance));

            // return 0;
        }
    }

    public String toString() {
        System.out.println("RollNo  IDnumber    Name    contactNo   emailID");

        return (String.valueOf(this.rollNo) + "    " + this.id + "  " + this.name + "    " + String.valueOf(this.getContactNumber()) + "  " + this.getEmailID() + "\n");
    }

    public static void main(String[] args) {
        Student student1 = new Student(9, "17IT203", "student_name", 9876543210l, "student@student.com");
        //debugging code
        System.out.println(student1.name);
        System.out.println(student1.rollNo);
        System.out.println(student1.id);
        System.out.println(student1.getContactNumber());
        System.out.println(student1.getEmailID());
        System.out.println(student1.getAttendance());

        student1.present();
        System.out.println(student1.getAttendance());
        
        student1.absent();
        System.out.println(student1.getAttendance());
    }
}