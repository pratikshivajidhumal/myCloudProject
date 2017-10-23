/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;


import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.credentials.CredentialsForApplication;
import com.db.FileInformationOfDatabase;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
 * @author Pratik Dhumal
 */
@MultipartConfig
public class MainMenuServlet extends HttpServlet {


    private static final String SUFFIX = "/";   
    private static boolean flagForNewFolder=true;
    String bucketName = "pratikbucketmumbai";
    boolean successFlag=false;
    AmazonS3Client s3Client=null;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try
        {
        
        ArrayList<String> arrayListOfFile;
        arrayListOfFile = new ArrayList<>();
        
      String userName= request.getSession(false).getAttribute("UserName").toString();
      
       String mainMenu="mainMenu.jsp";
        HttpSession hs=request.getSession();
      
        System.out.println("username from session is:"+userName);
     CredentialsForApplication credentials=new CredentialsForApplication();
              s3Client=credentials.setCredentials();
        
        
            
        List<Part> filePartsOfFile; 
        
        
        filePartsOfFile = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());

        
        ObjectListing listofFolders;
        listofFolders = s3Client.listObjects(bucketName);

      
       List<S3ObjectSummary> summaries = listofFolders.getObjectSummaries();
       listofFolders = s3Client.listNextBatchOfObjects (listofFolders);
       summaries.addAll (listofFolders.getObjectSummaries());
       
        for (S3ObjectSummary objectSummary : summaries) 
       {
	    String uniqueId = objectSummary.getKey();
            if(uniqueId.equals(userName))
            {   
                flagForNewFolder=false;
                break;
                
            }
            else
            {
                System.out.println("check2");
                flagForNewFolder=true;
            }
       }  
       
       if(flagForNewFolder)
        createFolder(bucketName, userName, s3Client);
       
          for(Part filePart : filePartsOfFile) 
         { 
          String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
         
          InputStream fileContent = filePart.getInputStream();
                
          StringWriter writer = new StringWriter();
          IOUtils.copy(fileContent,writer,"UTF-8");
          String theFileContent= writer.toString();
        
          File file = new File(fileName);         
          file.createNewFile();

          FileWriter fwriter = new FileWriter(file);
          fwriter.write(theFileContent);
          fwriter.close();
          
          String completeFilePath = userName + SUFFIX + fileName;
          
          s3Client.putObject(new PutObjectRequest(bucketName,completeFilePath,file));
          
          arrayListOfFile.add(fileName);
          
        } 
        
        FileInformationOfDatabase info=new FileInformationOfDatabase();
        info.addDataIntoDatabase(userName,arrayListOfFile);
        
         
         hs.setAttribute("Message","File uploadeded successfully");
         RequestDispatcher rd=request.getRequestDispatcher("MainMenu.jsp");  
         rd.forward(request, response);
              
              
              
        }
        catch(AmazonClientException | IOException | IllegalArgumentException | ServletException e)
        {
            
              e.printStackTrace();
        }
   
    }   
     
   
 public static void createFolder(String bucketName, String folderName, AmazonS3 client) {

	ObjectMetadata metadataObject = new ObjectMetadata();
	metadataObject.setContentLength(0);
	
	InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
	
	PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
				folderName + SUFFIX, emptyContent, metadataObject);
	
	client.putObject(putObjectRequest);
}
     

}
         
        
    
    
    


