import java.util.Scanner;

//code to run in the remote client 
public class worker_remote {
	//can be changed using arguments
static int num_threads=1;
static Q request;static Q response;
static table t;
static worker_thread[] th;
	public static void main(String[] args) throws Exception {
		if(args[0]!=null)
			num_threads=Integer.parseInt(args[0]);
		Scanner sc=new Scanner(System.in);
		//checking for sqs & DynamoDB
		System.out.println("pls enter sqs url");
		Q.myQueueUrl=sc.nextLine();
		request=new Q();response=new Q();
		//to find existing request & response queue  
		request.create("request");response.create("response");
		System.out.println("pls enter table name");
		table.tableName=sc.nextLine();
		t=new table();
		//to give the description of existing table
		t.create(); 
		//starting threads
		for(int i=0;i<num_threads;i++)
			th[i]=new worker_thread();
		//waiting for threads to join 
		for(int i=0;i<num_threads;i++)
			th[i].t.join(); 
	}

}
