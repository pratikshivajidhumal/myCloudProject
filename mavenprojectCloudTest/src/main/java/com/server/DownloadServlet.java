 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.credentials.CredentialsForApplication;
import com.sns.Notifications;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Pratik Dhumal
 */
public class DownloadServlet extends HttpServlet 
{
      AmazonS3Client s3Client=null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        
           System.out.println("In download servlet");
           String fileName=request.getParameter("name");
            System.out.println("fileName"+fileName);
           String userName= request.getSession(false).getAttribute("UserName").toString();
            String SUFFIX = "/";   
   
        String bucketName = "pratikbucketmumbai"; 
        String key;    
     //   String filePath = "F:\\";
          
        
        key = userName+SUFFIX+fileName;
        try
        {
            System.out.println("key is:"+key);
             CredentialsForApplication credentials=new CredentialsForApplication();
              s3Client=credentials.setCredentials();
            
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
            InputStream objectData = object.getObjectContent();
            
          
          
          InputStreamReader Ip=new InputStreamReader(objectData);
          

          StringWriter writer = new StringWriter();
          IOUtils.copy(objectData, writer, "UTF-8");
          String theFileContent = writer.toString();
          
          System.out.println("theFileContent"+theFileContent);
          
          //String totalfileName=filePath+ fileName;
          File file = new File(fileName);         
          file.createNewFile();

          FileWriter fwriter = new FileWriter(file);
          fwriter.write(theFileContent);
          fwriter.close();
          ServletContext context = getServletContext();
          String mimeType =null;
         
          if(fileName.contains(".pdf"))
          {
      
            mimeType = "application/pdf";
          }
          else
          {
              mimeType = "application/octet-stream";
          }
         
         FileInputStream inStream = new FileInputStream(file);
         response.setContentType(mimeType);
         response.setContentLength((int) file.length());
         
          String headerKey = "Content-Disposition";
          String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
          response.setHeader(headerKey, headerValue);
         
      
        
           OutputStream outStream = response.getOutputStream();
           
           
           System.out.println("data outStream is:"+outStream.toString());

         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.close(); 
          
          
          
            Notifications notification=new Notifications();
            notification.sendNotification();
          
            
       
        }
         catch (AmazonClientException ace) 
         {
            System.out.println("Caught an AmazonClientException, which means"+
            		" the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
          
        
       
        
    }
   
    
    

}