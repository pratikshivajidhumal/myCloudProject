/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db;

import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pratik dhumal
 */
public class DatabaseConnection 
{
    
    public Connection makeConnection()
    {
       Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("","","");  
            
           
            if(con!=null)
                System.out.println("connection successful");
            else
                System.out.println("connection failure");          
            
         
        }
        catch(ClassNotFoundException | SQLException e  )
        {
            System.out.println("Issue is"+e);
        }
     return con;   
    }
    public void closeConnection(Connection con)
    {
        if(con!=null)
        try 
            {
                con.close();
        } catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
}
