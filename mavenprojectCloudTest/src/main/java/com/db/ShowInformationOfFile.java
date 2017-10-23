/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import com.pojo.DatabaseFilePojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratik dhumal
 */
public class ShowInformationOfFile 
{
    
     public  ArrayList<DatabaseFilePojo> fetchData(String username)
    {
        System.out.println("check1");
       
        Connection connectionObject=null;
        PreparedStatement statementObject=null;
        ResultSet resultSetObject=null;
        
         ArrayList<DatabaseFilePojo> pojo=new ArrayList<DatabaseFilePojo>();
         DatabaseFilePojo pojoObject;
         
        String query="select * from file_info,user_info where foreign_key=(select primary_key_one from user_info where user_name=?) AND primary_key_one=(select primary_key_one from user_info where user_name=?)";
        
        DatabaseConnection databaseObject=new DatabaseConnection();
        connectionObject = databaseObject.makeConnection();
        
        System.out.println("check2");
        
        if(connectionObject!=null)
        {
            System.out.println("check3");
            try 
           {
               System.out.println("check4");
                statementObject=connectionObject.prepareStatement(query);
                statementObject = connectionObject.prepareStatement(query);
                statementObject.setString(1, username);
                statementObject.setString(2, username);
                
                
               resultSetObject = statementObject.executeQuery();
               System.out.println("check5"); 
                while(resultSetObject.next())
                {
                     System.out.println("check6");
                    pojoObject=new DatabaseFilePojo();
                    String file_name=resultSetObject.getString("file_name");
                    String file_upload_time=resultSetObject.getString("file_upload_time");
                    String file_description=resultSetObject.getString("file_description");
                    String first_name=resultSetObject.getString("first_name");
                    String last_name=resultSetObject.getString("last_name");
                    String file_update_time=resultSetObject.getString("file_update_time");
                    
                    if(file_update_time==null)
                        file_update_time="not updated yet";
                    pojoObject.setFileName(file_name);
                    pojoObject.setFileDescription(file_description);
                    pojoObject.setFileUploadTime(file_upload_time);
                    pojoObject.setFirstName(first_name);
                    pojoObject.setLastName(last_name);
                    pojoObject.setUpdateTime(file_update_time);
                    pojo.add(pojoObject);  
                    System.out.println("check7");
                }
                
              /*  for(int i=0;i<pojo.size();i++)
                {
                    System.out.println("pojo name:"+pojo.get(i).getFileName());
                    System.out.println("pojo getFileDescription:"+pojo.get(i).getFileDescription());
                    System.out.println("pojo name:"+pojo.get(i).getFileUploadTime());
                }*/
                
                
           } 
            catch (SQLException ex) 
            {
              ex.printStackTrace();
          }
            finally
            {
               
                    try 
                    {
                         if(resultSetObject!=null)
                             resultSetObject.close();
                          if(statementObject!=null)
                              statementObject.close();
                          if(connectionObject!=null)
                              databaseObject.closeConnection(connectionObject);
                 
                          System.out.println("check8");
                } catch (SQLException ex) 
                {
                    Logger.getLogger(LoginConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
        }
        return pojo;

    }
    
    
}
