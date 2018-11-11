class SupremeClass{
	private static Batch instance;
	public static Batch getBatch(){
		if(instance==null){
			instance=new Batch("/home/tejaswini/POP_project/studentdetails.csv");
		}
		return instance;
	}
}


class Test{
public static void main(String args[]){
	SupremeClass S=new SupremeClass();
	Batch B=S.getBatch();

        System.out.println(B);
}}