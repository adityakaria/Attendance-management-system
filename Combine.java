import java.util.*;
import java.io.*;
// import java.io.File;
// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.PrintWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;

class Combine{
	public static void main(String[] args) throws IOException{
		updateAttendace();								
	}

	//Updating attendance of each of the student who attended the lab, and incrementing the total labs attended.
	public static void updateAttendace(){
		SupremeClass sc= new SupremeClass();		
		Batch B= SupremeClass.getBatch();						
		for(Student stu:B.students){
			stu.totalLabs++;					//Incrementing the total labs held for each student, whenever a lab is held.
		}
		try {
			System.out.println("Enter Date(dd-mm-yy) for which the attendance has to be updated: ");
			BufferedReader kIP = new BufferedReader(new InputStreamReader(System.in));
			String dt;
			dt = kIP.readLine();
			File file = new File( "./srv/files", dt + ".csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			Student student1;
			String line = "";
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
				stud=thislab.addStudent(tempArr[0]);
				student1=B.getStudentById(tempArr[0]);
				stud.setReference(student1);
				student1.present();
			}
			br.close();

			//Saving the new attendance of each of the student in the file attendance.csv
			try{
				PrintWriter pw=new PrintWriter("attendence.csv","UTF-8");
				pw.println(String.valueOf(B.students[0].totalLabs));
				for(int i=0;i<B.classStrength;i++){
					pw.println(String.valueOf(B.students[i].getAttendence()));
				}	
				pw.close();
				System.out.println("Do you want to see the list of students who attended the lab today?");
				System.out.println("Enter Y for Yes and N for No");
				
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Printing  the details of the students who attended the lab today. We will later create a function to do this, whenever it is called.
	public static void LabDetails(){
		System.out.println("Enter Date(dd-mm-yy) for which you want the lab details: ");
		BufferedReader kIP = new BufferedReader(new InputStreamReader(System.in));
		String dt;
		dt = kIP.readLine();
		File file = new File( "./srv/files", dt + ".csv");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String[] tempArr;
		line= br.readLine();
		tempArr=line.split("-");
		Lab currentlab = new Lab(Integer.parseInt(tempArr[0]),Integer.parseInt(tempArr[1]),Integer.parseInt(tempArr[2]));
		line= br.readLine();
		tempArr=line.split(" ");
		currentlab.addMinLabDuration(Integer.parseInt(tempArr[0]));
		while ((line = br.readLine()) != null) {
			tempArr = line.split(",");
			stud=currentlab.addStudent(tempArr[0]);
			student1=B.getStudentById(tempArr[0]);
			stud.setReference(student1);
		}
		br.close();
		System.out.println("The details of the lab conducted on: " + dt);
		System.out.println("Minimum Duration of the lab: " + currentlab.getminLabDuration() + " hrs");					
		System.out.println("The list of students who attended the lab is:");
		currentlab.studentsAttended();
	}
}