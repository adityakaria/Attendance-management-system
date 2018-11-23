import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Combine{
	public static void main(String[] args) {
		SupremeClass sc= new SupremeClass();		
		Batch B= sc.getBatch();						
		for(Student stu:B.students){
			stu.totalLabs++;						//isn't this is being done 2 times? Once when the present method is called and once here.
		}												
		Student student;							//this can be removed
		try {//for the lab
			File file = new File("lab.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Student student1;
			String line = "";
			String[]  tempTimeArr;
			StudentInLab stud;

			String[] tempArr;
			line= br.readLine();
			tempArr=line.split("-");
			Lab thislab = new Lab(Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[1]),Integer.parseInt(tempArr[2]));
			line= br.readLine();
			tempArr=line.split(" ");
			thislab.addMinLabDuration(Integer.parseInt(tempArr[0]));
			while ((line = br.readLine()) != null) {
				tempArr = line.split(",");
				tempTimeArr=tempArr[2].split(":");
				Time intime= new Time(Integer.parseInt(tempTimeArr[0]),Integer.parseInt(tempTimeArr[1]),Integer.parseInt(tempTimeArr[2]));
				//tempArr = line.split(",");
				tempTimeArr=tempArr[3].split(":");
				Time outtime= new Time(Integer.parseInt(tempTimeArr[0]),Integer.parseInt(tempTimeArr[1]),Integer.parseInt(tempTimeArr[2]));
				stud=thislab.addStudent(Integer.parseInt(tempArr[1]),tempArr[0],intime,outtime);

				student1=B.getStudentById(tempArr[0]);

				stud.setReference(student1);
				student1.present();
				//student1.labsAttended++;
			}
			try{
				File file1=new File("attendence.csv");
				
			
				
				//FileWriter fileWritter = new FileWriter(file1.getName(),true);
         		//BufferedWriter bw = new BufferedWriter(fileWritter);
				PrintWriter pw=new PrintWriter("attendence.csv","UTF-8");
				pw.println(String.valueOf(B.students[0].totalLabs));
				for(int i=0;i<B.classStrength;i++){
					pw.println(String.valueOf(B.students[i].getAttendence()));

				}
         			
				
				pw.close();
				}
			
		
			

			catch(IOException e){
				e.printStackTrace();
			}
			
			
			thislab.studentsAttended();
			br.close();
		}
		 catch (IOException ex) {
			ex.printStackTrace();
		}

}
}
