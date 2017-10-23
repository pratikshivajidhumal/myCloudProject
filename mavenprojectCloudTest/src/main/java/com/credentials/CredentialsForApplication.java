/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credentials;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sns.AmazonSNSClient;

/**
 *
 * @author Pratik Dhumal
 */
public class CredentialsForApplication 
{
 public AmazonS3Client setCredentials()
 {
      AmazonS3Client s3ClientPratik = null;   
     try
     {
                AWSCredentials credentials = new BasicAWSCredentials(
				"", 
				"");
        
        
        s3ClientPratik = new AmazonS3Client(credentials);
        s3ClientPratik.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
        s3ClientPratik.setEndpoint("s3.ap-south-1.amazonaws.com");
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
  return s3ClientPratik;      
 }
 public AmazonSNSClient setCredentialsForSNS()
 {
     AmazonSNSClient snsClient = null;
     try
     {
         AWSCredentials credentials = new BasicAWSCredentials(
				"", 
				"");
         
            
             snsClient= new AmazonSNSClient(credentials);		                           
            snsClient.setRegion(Region.getRegion(Regions.US_WEST_1));
            
            
         
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     return snsClient;
 }
    
}
