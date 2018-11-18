class SupremeClass{
	private static Batch instance;
	public static Batch getBatch(){
		if(instance==null){
			instance=new Batch("studentdetails.csv","attendence.csv");
		}
		return instance;
	}
}


public class Test{					//This whole thing can be removed.
public static void main(String args[]){
	SupremeClass S=new SupremeClass();
	Batch B=S.getBatch();

        System.out.println(B);

        for(int i=0;i<B.classStrength;i++){
        	System.out.println("roll number "+(i+1)+" attendence is "+B.students[i].labsAttended);
        }
}}