/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.db.ShowInformationOfFile;
import com.pojo.DatabaseFilePojo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Pratik Dhumal
 */
public class FetchDataFromDatabase extends HttpServlet {

    private ArrayList<DatabaseFilePojo> ObjectOfDatabaseFile;

  

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String username;
        String showMyListOfFiles="showMyListOfFiles.jsp";
        username = request.getSession(false).getAttribute("UserName").toString();
        ShowInformationOfFile show=new ShowInformationOfFile();
        ObjectOfDatabaseFile=show.fetchData(username);
        request.setAttribute("DatabaseValues",ObjectOfDatabaseFile);
        RequestDispatcher dispatcher = request.getRequestDispatcher(showMyListOfFiles);
        dispatcher.forward(request, response);
        
    }


}
