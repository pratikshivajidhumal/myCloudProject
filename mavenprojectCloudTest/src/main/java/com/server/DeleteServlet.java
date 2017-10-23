/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.credentials.CredentialsForApplication;
import com.db.DeleteDatabaseEntry;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pratik dhumal
 */
public class DeleteServlet extends HttpServlet 
{

   
    
     String SUFFIX = "/";  
      
         String bucketName = "pratikbucketmumbai"; 
         AmazonS3Client s3Client;       
         String keyName;
         
         String  errorMessage = "File deleted successfully";
         String successMessage = "Error while deleting files";

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
          String userName= request.getSession(false).getAttribute("UserName").toString();
          String fileName=request.getParameter("name");
          String showMyListOfFiles="showMyListOfFiles.jsp";
          
          HttpSession hs=request.getSession();
          
          System.out.println("userName:"+userName);
          
       keyName=userName+SUFFIX+fileName;

      
          try
          {
              CredentialsForApplication credentials=new CredentialsForApplication();
              s3Client=credentials.setCredentials();
       
             s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
            
              DeleteDatabaseEntry entry=new DeleteDatabaseEntry();
              entry.deleteFile(fileName);
              
               System.out.println("successful delete::::::");
               
                hs.setAttribute("Message","File deleted successfully");
     
              
              RequestDispatcher rd=request.getRequestDispatcher(showMyListOfFiles);  
              rd.forward(request, response);
              
             
            
    }            catch (AmazonServiceException ase) 
                    {
                        
                        
            System.out.println("Caught an AmazonServiceException.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
             
     
              request.getSession().setAttribute("Message",successMessage);
              RequestDispatcher rd=request.getRequestDispatcher(showMyListOfFiles);  
              rd.forward(request, response);
            
        }
          catch (AmazonClientException ace) 
        {
            System.out.println("Caught an AmazonClientException.");
            System.out.println("Error Message: " + ace.getMessage());
            
              
        }
        
  
    }

}
