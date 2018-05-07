import com.amazonaws.services.sqs.model.Message;

public class client_response implements Runnable{
	static Thread t;String task;
	int item_count=0;
	client_response(){
		t=new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while(item_count!=client_request.num_task){
			//Retrieving response (remove() retrieves at most 10 messages at a time)
			client_remote.response.remove(); 
			//checking results
			for (Message message : Q.messages){
				if(message.getBody()=="true")
					continue;
				else
					System.out.println("task failed\n");
			}
		} 
		Client.enlapsed_time=System.currentTimeMillis()-Client.enlapsed_time;
		System.out.println("num_task="+client_put.num_task+"\t"+"time(sec)="+(double)Client.enlapsed_time/1000);
		System.out.println("Throughput="+(client_put.num_task*1000/Client.enlapsed_time));
		System.out.print("purging both SQS-queues & deleting DynamoDB-table & worker");
		client_remote.request.purge();
		client_remote.response.purge();
		client_remote.t.delete();
		client_remote.w.delete_worker();
	}
}
