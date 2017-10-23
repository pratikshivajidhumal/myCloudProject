package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratik Dhumal
 */
public class UpdateFileDatabase 
{
    public boolean updateFileInformation(String userName,String fileName)
    {
        Connection connectionObject=null;
        PreparedStatement statementObject=null;
        ResultSet resultSetObject=null;
        
        System.out.println("File name in database file is"+fileName);
        
         PreparedStatement statementObjectUpdate=null;
         PreparedStatement statementObjectInsert=null;
         
         boolean flagForUpdate=false;
        
         
         
         
        String query="select * from file_info where file_name=?";
        
        DatabaseConnection databaseObject=new DatabaseConnection();
        connectionObject = databaseObject.makeConnection();
        
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         String dateString=dateFormat.format(date);
      
        
        if(connectionObject!=null)
        {
            try 
           {
                statementObject=connectionObject.prepareStatement(query);
                statementObject = connectionObject.prepareStatement(query);
                statementObject.setString(1,fileName);
                resultSetObject=statementObject.executeQuery();
  
                if(resultSetObject.next())
                {
                    System.out.println("Update");
                    String queryOfUpdate="update file_info SET file_update_time=? where file_name=?";
                    statementObjectUpdate = connectionObject.prepareStatement(queryOfUpdate);                     
                    statementObjectUpdate.setString(1, dateString);
                    statementObjectUpdate.setString(2, fileName);
                    statementObjectUpdate.execute();
                    flagForUpdate=true;

                }
                else
                {
                    System.out.println("Insert");
                    String queryOfInsert="INSERT INTO file_info(foreign_key,file_name,file_upload_time,file_description) VALUES ( (SELECT primary_key_one from user_info WHERE user_name=?),?,?,?)";
                    statementObjectInsert = connectionObject.prepareStatement(queryOfInsert);                     
                    statementObjectInsert.setString(1, userName);
                    String fileDescription=fileName.substring(fileName.lastIndexOf(".") + 1);
                    statementObjectInsert.setString(2,fileName );
                    statementObjectInsert.setString(3,dateString );
                    statementObjectInsert.setString(4, fileDescription);
                    statementObjectInsert.execute();
                    flagForUpdate=false;
                     
                }
                
                
           } 
            catch (SQLException ex) 
            {
                System.out.println("Exception is:"+ex);
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
                          
                } catch (SQLException ex) 
                {
                    Logger.getLogger(LoginConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
           
        
        } 
        
        
         return flagForUpdate;
        
    }
    
    
}
