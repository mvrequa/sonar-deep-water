package org.requa.app;

import java.io.IOException;

import org.apache.log4j.Logger;

import javax.jms.JMSException;

import org.requa.app.sqslib.TextMessageSender;
import org.requa.app.sqslib.AsyncMessageReceiver;
import org.requa.app.s3lib.UploadObjectSingleOperation;
import org.requa.app.s3lib.GetObject;

public class App 
{
    /// This app takes messages out of the SQS queue and saves them in S3, periodically

    final static Logger logger = Logger.getLogger(App.class);

    public static void main( String[] args ) throws JMSException
    {
        
        App obj = new App();
        obj.runMe("requalog -- 001");
                
        //SQSReader reader = new SQSReader();
        //reader.createQueue("TestQueue");

        //TextMessageSender tms = new TextMessageSender();
        //tms.main(args);
        
        //AsyncMessageReceiver amr = new AsyncMessageReceiver();
        //try {
        //    amr.main(args);
        //} catch (Exception exc) {
        //    // TODO - handle this
        //}
    
        //UploadObjectSingleOperation uoso = new UploadObjectSingleOperation();
        //try {
        //    uoso.main(args);
        //} catch (IOException ioexc) {
        //    // TODO - handle this
        //}

        GetObject getobj = new GetObject();
        try {
            getobj.main(args);
        } catch (IOException ioexc) {
            // TODO - handle this
        }

    }
    
        
    private void runMe(String parameter){
 
        if(logger.isDebugEnabled()){
            logger.debug("This is debug : " + parameter);
        }
 
        if(logger.isInfoEnabled()){
            logger.info("This is info : " + parameter);
        }
 
        logger.warn("This is warn : " + parameter);
        logger.error("This is error : " + parameter);
        logger.fatal("This is fatal : " + parameter);
 
    }
}