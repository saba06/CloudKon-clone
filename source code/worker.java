public class worker implements Runnable{
Thread t;String task;
worker(){
		t=new Thread(this);
		t.start();
}

	@Override
	public void run(){
			while(true){
			if(!client_local.request.isEmpty()){	
				task=client_local.request.remove();
			String[] element=task.split(" ");
			try {
				Thread.sleep(Integer.parseInt(element[2]));
				//response id-0 (for success)
				client_local.response.add(element[0]+"-"+Integer.toString(0));
				//System.out.println(element[2]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}else if(client_local.run!=true)
				break;
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			
			}//System.out.println("worker_out");
	}

}
