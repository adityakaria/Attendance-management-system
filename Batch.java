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

    public String toString() {
        String info = "\n";
        System.out.printf("%-8s | %-10s | %-15s | %-15s | %-25s\n","Roll No.", "ID Number", "Name", "Contact Number","Email ID");
        System.out.printf("------------------------------------------------------------------------------------\n");
        for (int i = 0; i < this.classStrength; i++) {
            System.out.printf("%5d     | %-10s | %-15s | %-15d | %-25s\n", students[i].rollNo, students[i].id, students[i].name, students[i].getContactNumber(), students[i].getEmailID());
        }
        return " ";
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
