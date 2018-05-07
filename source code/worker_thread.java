import com.amazonaws.services.sqs.model.Message;

//worker thread in remote
public class worker_thread implements Runnable{
	Thread t;String[] task; boolean duplicate;
	worker_thread(){
		t=new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while((Q.sqs.getQueueUrl("request")!=null) && (Q.sqs.getQueueUrl("response")!=null)){
			worker_remote.request.remove();
			for (Message message : Q.messages){
				//check with DynamoDb for duplicates
				duplicate=worker_remote.t.scan(message.getMessageId());
				if(duplicate==true)
					continue;
				else{
					task=message.getBody().split(" ");
					try {
						Thread.sleep(Integer.parseInt(task[1]));
					} catch (NumberFormatException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//writing response
					worker_remote.response.insert("true");
				}
			}
		}
	}

}
