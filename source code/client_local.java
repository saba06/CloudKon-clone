import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class client_local {
	static int N;
	static worker[] w;
	//request & response queue
	static ConcurrentLinkedQueue<String> request;
	static ConcurrentLinkedQueue<String> response;
	//flag to stop worker
	static boolean run=true;
	client_local(String num_threads) throws IOException, InterruptedException{
		N=Integer.parseInt(num_threads);
		//a dynamic in memory request queue
		request=new ConcurrentLinkedQueue<String>(); 
		//a dynamic in memory response queue
		response=new ConcurrentLinkedQueue<String>();
		//Creating worker threads first
		w=new worker[N];
		for(int i=0;i<N;i++)
			w[i]=new worker();
		//creating client
		client_put c_put=new client_put();
		c_put.t.join();
		}
}
