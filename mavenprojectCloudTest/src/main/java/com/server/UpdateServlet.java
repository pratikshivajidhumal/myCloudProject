    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.credentials.CredentialsForApplication;
import com.db.UpdateFileDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author pratik Dhumal
 */
@MultipartConfig
public class UpdateServlet extends HttpServlet 
{

     String SUFFIX = "/";  
      
         String bucketName = "pratikbucketmumbai"; 
         AmazonS3Client s3Client;       
         String keyName;
         
         String  errorMessage = "Problem while file Insertion";
         String successMessage = "File updated successfully";
         
   

    boolean successFlag=false;

   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
         
          String fileNameRequest=request.getParameter("name");          
          request.setAttribute("fileNameRequest", fileNameRequest);
          doPost(request, response);
   }*/
    
    @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
   {
       String showMyListOfFiles="showMyListOfFiles.jsp";
       HttpSession hs=request.getSession();
       ArrayList<String> arrayListOfFile;
       arrayListOfFile = new ArrayList<>();
       String userName= request.getSession(false).getAttribute("UserName").toString();
       String fileNameRequest;
        
       
        try
        {
               System.out.println("username from session is:"+userName);
               CredentialsForApplication credentials=new CredentialsForApplication();
               s3Client=credentials.setCredentials();
            
            
            
       Part filePart = request.getPart("file"); 
    
   
    
          InputStream fileContent = filePart.getInputStream();
            System.out.println("name1:"+filePart.getName());
          
          fileNameRequest=filePart.getSubmittedFileName();
         
          
           System.out.println("name2:"+filePart.getSubmittedFileName());
          
            System.out.println("Name of the file is:"+fileNameRequest);
          
          StringWriter writer = new StringWriter();
          IOUtils.copy(fileContent,writer,"UTF-8");
          String theFileContent= writer.toString();
        
          File file = new File(fileNameRequest);         
          file.createNewFile();

          FileWriter fwriter = new FileWriter(file);
          fwriter.write(theFileContent);
          fwriter.close();
          
          String completeFilePath = userName + SUFFIX + fileNameRequest;
          
          s3Client.putObject(new PutObjectRequest(bucketName,completeFilePath,file));
          
          UpdateFileDatabase updateObject=new UpdateFileDatabase();
          boolean flagUpdate=updateObject.updateFileInformation(userName,fileNameRequest);
            
          if(flagUpdate)
         hs.setAttribute("Message","File updated successfully");
          else
         hs.setAttribute("Message","File inserted successfully");
          
         RequestDispatcher rd=request.getRequestDispatcher("showMyListOfFiles.jsp");  
         rd.forward(request, response);
              
              
              
        }
        catch(AmazonClientException | IOException | IllegalArgumentException | ServletException e)
        {
            
              e.printStackTrace();
        }
       
   }

}
