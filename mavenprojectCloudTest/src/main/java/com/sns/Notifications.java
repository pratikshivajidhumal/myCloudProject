/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sns;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.credentials.CredentialsForApplication;

/**
 *
 * @author prati
 */
public class Notifications 
{
    
    public void sendNotification()
    {
         
        
         try
         {
   
             CredentialsForApplication cred=new CredentialsForApplication();
             AmazonSNSClient snsClient = cred.setCredentialsForSNS();
           
            

     String topicArn = "arn:aws:sns:us-west-1:291902413373:cali";


String msg = "Please check your s3 bucket for recent download activities";
PublishRequest publishRequest = new PublishRequest(topicArn, msg);
PublishResult publishResult = snsClient.publish(publishRequest);

System.out.println("MessageId - " + publishResult.getMessageId());

       
        
    }
    catch(Exception e)
    {
     e.printStackTrace();
    }
    }
    
}
