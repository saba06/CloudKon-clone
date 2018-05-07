import java.io.IOException;

public class client_request implements Runnable{
	//counter for number of task found in work file & default id
		static int num_task=0;
		static Thread t;String task;
		
		client_request() throws IOException {
			t=new Thread(this);
				t.start();
		}
		@Override
		public void run(){
			//Client request thread
					String line;
					try {
						Client.enlapsed_time=System.currentTimeMillis();
						while((line=Client.br.readLine())!=null){
							//an auto-incrementing task_id
							num_task++;
							//making the request
							client_remote.request.insert(line);
						}
						Client.br.close();
						Client.fr.close();
						//System.out.println("Client out");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		}

}
