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
public class RegistrationDatabase 
{
 
     public boolean register(String firstName,String lastName,String userName,String password,String email)
    {
        boolean flagForRegistration=false ;
        Connection connectionObject=null;
        PreparedStatement statementObject=null;
        ResultSet resultSetObject=null;
        
        String query="INSERT INTO user_info(first_name,last_name,user_name,password,email) VALUES (?,?,?,?,?)";
        
        DatabaseConnection databaseObject=new DatabaseConnection();
        connectionObject = databaseObject.makeConnection();
        
        if(connectionObject!=null)
        {
            try 
           {
                statementObject=connectionObject.prepareStatement(query);
                System.out.println("Query is:"+query);
                statementObject = connectionObject.prepareStatement(query);
                statementObject.setString(1, firstName);
                statementObject.setString(2, lastName);
                statementObject.setString(3, userName);
                statementObject.setString(4, password);
                statementObject.setString(5, email);
                
              flagForRegistration= statementObject.execute();
  
                
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
  
        return flagForRegistration;
    }
    
}
