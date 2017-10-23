/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratik Dhumal
 */
public class DeleteDatabaseEntry 
{
     public void deleteFile(String fileName)
    {
       
        Connection connectionObject=null;
        PreparedStatement statementObject=null;
        ResultSet resultSetObject=null;
        
        String query="DELETE FROM file_info where file_name=?";
        
        DatabaseConnection databaseObject=new DatabaseConnection();
        connectionObject = databaseObject.makeConnection();
        
        if(connectionObject!=null)
        {
            try 
           {
                statementObject=connectionObject.prepareStatement(query);
                
                statementObject = connectionObject.prepareStatement(query);
                statementObject.setString(1,fileName);
                
                System.out.println("Query is:"+query);
               
                statementObject.execute();
  
                
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
    }
}
    
    

