package org.requa.app;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;

public class TextMessageSender {

    final static Logger logger = Logger.getLogger(TextMessageSender.class);


    public static void main(String args[]) throws JMSException {

        ExampleConfiguration config = ExampleConfiguration.parseConfig("TextMessageSender", args);
        
        ExampleCommon.setupLogging();
        
        // Create the connection factory based on the config
        SQSConnectionFactory connectionFactory = 
                SQSConnectionFactory.builder()
                    .withRegion(config.getRegion())
                    .withAWSCredentialsProvider(config.getCredentialsProvider())
                    .build();
        
        // Create the connection
        SQSConnection connection = connectionFactory.createConnection();
        
        // Create the queue if needed
        ExampleCommon.ensureQueueExists(connection, config.getQueueName());
            
        // Create the session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer( session.createQueue( config.getQueueName() ) );
        
        sendMessages(session, producer);

        // Close the connection. This will close the session automatically
        connection.close();
        System.out.println( "Connection closed" );
    }

    private static void sendMessages( Session session, MessageProducer producer ) {
        
        try {
            String input;
            while( true ) { 
                String msg = "Hello World";
                                
                TextMessage message = session.createTextMessage(msg);
                producer.send(message);
                System.out.println( "Send message " + message.getJMSMessageID() );
                break;
            }
        } catch (JMSException e) {
            System.err.println( "Failed sending message: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}