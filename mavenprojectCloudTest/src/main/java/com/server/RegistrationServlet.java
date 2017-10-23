/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.db.RegistrationDatabase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pratik Dhumal
 */
public class RegistrationServlet extends HttpServlet 
{

   
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String firstName;
        String lastName;
        String userName;
        String password;
        String email;
        boolean flagForRegistration=false;
        String loginPage="Login.jsp";
        
        HttpSession ss=request.getSession(true);
        
        
        
        firstName= request.getParameter("firstName").toString();
        lastName=request.getParameter("lastName").toString();
        userName= request.getParameter("userName").toString();
        password=request.getParameter("password").toString();
        email=request.getParameter("email").toString();
        
        RegistrationDatabase databaseObject=new RegistrationDatabase();
        databaseObject.register(firstName, lastName, userName, password,email);
        
        if(flagForRegistration)
         ss.setAttribute("registerFlag","Registration successful go to log in page");
        else
        ss.setAttribute("registerFlag","Registration failed username already present,please try again with diffrent username");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
        dispatcher.forward(request, response);
       
       
         
    }

}
