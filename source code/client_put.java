import java.io.IOException;

public class client_put implements Runnable{
	//counter for number of task found in work file & default id
	static int num_task=0;
	static Thread t;String task;
	
	client_put() throws IOException {
		t=new Thread(this);
			t.start();
	}
	@Override
	public void run(){
		// request thread
				String line;
				try {
					Client.enlapsed_time=System.currentTimeMillis();
					while((line=Client.br.readLine())!=null){
						//an auto-incrementing task_id
						num_task++;
						client_local.request.add(Integer.toString(num_task)+" "+line);
					}//checking response 
					while(client_local.response.size()!=num_task)
						continue;
					//finding time
					Client.enlapsed_time=System.currentTimeMillis()-Client.enlapsed_time;
					
					client_local.run=false;
					
					System.out.println("\nnumber of workers="+client_local.N);
					System.out.println("num_task="+client_put.num_task+"\t"+"time(sec)="+(double)Client.enlapsed_time/1000);
					System.out.println("Throughput="+(client_put.num_task*1000/Client.enlapsed_time));
					Client.br.close();
					Client.fr.close();
					//System.out.println("Client out");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	}

}
