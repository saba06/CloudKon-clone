import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import java.awt.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
/*creates workers using an ami specified*/
public class create_worker {
	
static RunInstancesRequest runInstancesRequest;
static DescribeInstancesRequest describeInstancesRequest;

	create_worker(int num_worker) {
	//configuration
	runInstancesRequest = new RunInstancesRequest();
		runInstancesRequest.withImageId("ami-f2c4229f")
        .withInstanceType("t2.micro")
        .withMinCount(num_worker)
        .withMaxCount(num_worker)
        .withKeyName("key")
        .withSecurityGroups("launch-wizard-22");
		//launch
		AmazonEC2Client amazonEC2Client=new AmazonEC2Client();
					  RunInstancesResult runInstancesResult =
				      amazonEC2Client.runInstances(runInstancesRequest);
		System.out.println("\nrequried workers have been creates"+runInstancesResult);
	}
	public void delete_worker(){
		describeInstancesRequest = new DescribeInstancesRequest(); 
		TerminateInstancesRequest terminateInstanceRequest=new TerminateInstancesRequest().withInstanceIds(describeInstancesRequest.getInstanceIds());
	}
}
