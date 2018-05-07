import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Client {
	static String work_file;
	//file handlers for the work_file
			static FileReader fr;
			static BufferedReader br;
	//enlapsed time
			static	long enlapsed_time;
	public static void main(String[] args) throws Exception {
		//switch for selection of local Vs remote
		switch(args[0]){
		case "REMOTE":{
			work_file=args[1];
			fr=new FileReader(work_file);br=new BufferedReader(fr);	
			client_remote obj=new client_remote();
			break;}
		case "LOCAL":{
			work_file=args[2];
			fr=new FileReader(work_file);br=new BufferedReader(fr);
			client_local obj=new client_local(args[1]);
			break;}
		default :{
			System.out.println("pls enter the first argument as REMOTE or LOCAL");break;}
		}	
	}

}
