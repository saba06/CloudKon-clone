import java.io.IOException;
import java.util.Scanner;

/*creates  sqs queue
 * creates dynamo db
 * creates workers 
 * creates client 
 * terminates workers & sqs & dyanmoDB
 */
public class client_remote {
	static Q request;
	static Q response;
	static table t;
	static create_worker w;
	client_remote() throws Exception{
	//creating sqs
		request=new Q();
		response=new Q();
		request.create("request");response.create("response");
	//creating dynamo db
		t=new table();t.create();
	//create worker
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the number of workeres");
		w= new create_worker(sc.nextInt());
	//create client
		System.out.println("before we procede make sure:\n-workers are healthy and running"
				+ "\n-you have running threads at worker with url of sqs"
				+"\n\n enter 1 to continue 0 to terminate");
		if(sc.nextInt()==1){
			//creating client request & response threads
			client_request c_request=new client_request();
			client_response c_response=new client_response();
			//response thread terminates workers & sqs & dynamoDB
		}else{
			request.delete();response.delete();
			t.delete();w.delete_worker();
		}
		sc.close();
	}
}
