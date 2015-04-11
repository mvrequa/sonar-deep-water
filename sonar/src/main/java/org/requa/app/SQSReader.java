package org.requa.app;

import javax.jms.JMSException;

import org.apache.log4j.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;


public class SQSReader  
{
    /*
    *
    * This app takes messages out of the SQS queue and saves them in S3, periodically
    *
    */

    final static Logger logger = Logger.getLogger(App.class);
 
    
    public void createQueue(String parameter) throws JMSException
    {
        // Create the connection factory using the environment variable credential provider.
        // Connections this factory creates can talk to the queues in us-east-1 region. 
        
        SQSConnectionFactory connectionFactory =
            SQSConnectionFactory.builder()
                .withRegion(Region.getRegion(Regions.US_EAST_1))
                .withAWSCredentialsProvider(new EnvironmentVariableCredentialsProvider())
                .build();
 
        // Create the connection.
        SQSConnection connection = connectionFactory.createConnection();
        // Get the wrapped client
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();
 
 
        // TODO : doesn't handle error (400) AWS.SimpleQueueService.QueueDeletedRecently, fix
        // Create an SQS queue named 'TestQueue' – if it does not already exist.
        if (!client.queueExists(parameter)) {
                client.createQueue(parameter); 
        }
        

 

    }
}

