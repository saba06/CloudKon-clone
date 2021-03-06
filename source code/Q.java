import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.PurgeQueueRequest;

public class Q {
static String myQueueUrl ;
static AmazonSQS sqs ;
static List<Message> messages ;
	Q(){ }
	public void create(String MyQueue){

		/*
	     * The ProfileCredentialsProvider will return your [default]
	     * credential profile by reading from the credentials file located at
	     * (/home/sb/.aws/credentials).
	     */
	    AWSCredentials credentials = null;
	    try {
	        credentials = new ProfileCredentialsProvider("default").getCredentials();
	    } catch (Exception e) {
	        throw new AmazonClientException(
	                "Cannot load the credentials from the credential profiles file. " +
	                "Please make sure that your credentials file is at the correct " +
	                "location (/home/sb/.aws/credentials), and is in valid format.",
	                e);
	    }

	    sqs = new AmazonSQSClient(credentials);
	    Region usEast1 = Region.getRegion(Regions.US_EAST_1);
	    sqs.setRegion(usEast1);


	    try {
	        // Create a queue
	        System.out.println("Creating a new SQS queue called"+MyQueue+".\n");
	        CreateQueueRequest createQueueRequest = new CreateQueueRequest(MyQueue);
	        //returns url for existing once
	        myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();

	        // List queues
	        System.out.println("Listing all queues in your account.\n");
	        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
	            System.out.println("  QueueUrl: " + queueUrl);
	        }
	        System.out.println();
	    }catch (AmazonServiceException ase) {
	        System.out.println("Caught an AmazonServiceException, which means your request made it " +
	                "to Amazon SQS, but was rejected with an error response for some reason.");
	        System.out.println("Error Message:    " + ase.getMessage());
	        System.out.println("HTTP Status Code: " + ase.getStatusCode());
	        System.out.println("AWS Error Code:   " + ase.getErrorCode());
	        System.out.println("Error Type:       " + ase.getErrorType());
	        System.out.println("Request ID:       " + ase.getRequestId());
	    } catch (AmazonClientException ace) {
	        System.out.println("Caught an AmazonClientException, which means the client encountered " +
	                "a serious internal problem while trying to communicate with SQS, such as not " +
	                "being able to access the network.");
	        System.out.println("Error Message: " + ace.getMessage());
	    }


	}
public void insert(String message){
	// Send a message
    sqs.sendMessage(new SendMessageRequest(myQueueUrl, message));

	}
public List<Message> remove(){
	// Receive messages
    ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
    messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
    for (Message message : messages) {
        System.out.println("  Message");
        System.out.println("    MessageId:     " + message.getMessageId());
        System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
        System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
        System.out.println("    Body:          " + message.getBody());
        for (Entry<String, String> entry : message.getAttributes().entrySet()) {
            System.out.println("  Attribute");
            System.out.println("    Name:  " + entry.getKey());
            System.out.println("    Value: " + entry.getValue());
        }
    }return messages;
}
public void delete(){
	// Delete a queue
    System.out.println("Deleting the queue.\n");
    sqs.deleteQueue(new DeleteQueueRequest(myQueueUrl));
	}
public void purge(){
	//purge queue
	System.out.println("purge queue"); 
	PurgeQueueRequest purgequeueRequest= new PurgeQueueRequest(myQueueUrl);
	sqs.purgeQueue(purgequeueRequest);
	}
}
