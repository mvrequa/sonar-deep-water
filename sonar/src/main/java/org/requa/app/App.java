package org.requa.app;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.JMSSecurityException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import com.amazonaws.regions.Regions;
import com.amazonaws.regions.Region;
import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.SQSConnectionFactory;
//import com.amazonaws.services.sqs.model.*;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProvider;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JMSException
    {
        System.out.println( "Hello World!" );
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
 
        // Create an SQS queue named 'TestQueue' – if it does not already exist.
        if (!client.queueExists("TestQueue")) {
            client.createQueue("TestQueue");  
        }
 
    }
}
