import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Batch {
    // class Identification Parameters
    //private static Batch instance;
    int classStrength;
    String branch;
    int Gradyear;
    Student[] students;

    public Batch(String path1,String path2) {
        try{
        	int num=0,number;
            File file1=new File(path1);
            File file2=new File(path2);
            FileReader fr1=new FileReader(file1);
            FileReader fr2=new FileReader(file2);
            BufferedReader br1=new BufferedReader(fr1);
            BufferedReader br2=new BufferedReader(fr2);
            String line="";
            String[] temparr,info;
            line=br1.readLine();
            temparr=line.split(",");

            this.classStrength = Integer.parseInt(temparr[2]);
            this.branch = temparr[0];
            this.Gradyear = Integer.parseInt(temparr[1]);
            this.students = new Student[this.classStrength];
            number=Integer.parseInt(br2.readLine());

            for(int i=0;i<this.classStrength;i++){
                line=br1.readLine();
                info=line.split(",");
                this.students[i] = new Student(Integer.parseInt(info[0]), info[1], info[2], Long.parseLong(info[3]), info[4]);
                num=Integer.parseInt(br2.readLine());
                this.students[i].setAttendence(num);
                this.students[i].totalLabs=number;
            }
        
            br1.close();
            br2.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    // Add details of all students of the class based on the class Strength
    //public void setStudentDetails() 
        //System.out.println("Please Enter the student details...(tab separated)");
        //System.out.println("RollNo  IDnumber    Name    ContactNo   EmailID");

        //for (int i = 0; i < this.classStrength; i++) {
            //Scanner in = new Scanner(System.in);
            //String[] info = in.nextLine().split(" ");
            //students[i] = new Student(Integer.parseInt(info[0]), info[1], info[2], Long.parseLong(info[3]), info[4]);
        

        //System.out.println("");
    

    // default printable method
    public String toString() {
        String info = "\n";
        System.out.println("RollNo  IDnumber    Name    contactNo   emailID");
        
        for (int i = 0; i < this.classStrength; i++) {
            info += (String.valueOf(students[i].rollNo) + "    " + students[i].id + "   " + students[i].name + "    " + String.valueOf(students[i].getContactNumber()) + "   " + students[i].getEmailID() + "\n");
        }
        return info;
    }
    


    public Student getStudentById(String id){
        for(int i=0;i<this.classStrength;i++){
            if(id.equals(this.students[i].id)){
                return this.students[i];
            }

        }
        return null;
    }
}
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
        
//         System.out.println("Please enter the following details: (tab separated)");
//         System.out.println("Batch department    year    Class Strength");
//         String[] batch = in.nextLine().split(" ");

//         Batch b1 = new Batch(batch[0], Integer.parseInt(batch[1]), Integer.parseInt(batch[2]));
        
//         b1.setStudentDetails();
//         System.out.println(b1);
//     }

// }

// 1 17IT201 abc 1092348798 khfas@jaldsf.co
// 2 7ybad adusih 3128423412 sjdkahf@asfidh.com
// 3 fh9dj ajdhfa 9812749380 adusfi@daf.com
// 4 aip3 ishdfa 9108473138 akhsf@adf.com