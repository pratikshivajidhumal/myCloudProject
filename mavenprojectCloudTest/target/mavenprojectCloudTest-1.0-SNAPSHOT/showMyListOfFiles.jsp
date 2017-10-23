<%-- 
    Document   : showMyListOfFiles
    Created on : Oct 19, 2017, 5:04:44 PM
    Author     : Pratik Dhumal
--%>


<%@page import="com.db.ShowInformationOfFile"%>
<%@page import="com.pojo.DatabaseFilePojo"%>
<%@page import="java.util.ArrayList"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><link rel="stylesheet" href="LoginCss.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <h1><%=session.getAttribute("UserName")%>&nbsp;File information table</h1> 
    <h3><a href="Login.jsp">logout</a></h3>
    </head>
    <body>

        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p><a href="showMyListOfFiles.jsp"><input type="button"  value="Refresh"/></a></p>
        
        <table width="90%" border="1" id="TablePratik" name="TablePra tik" value="TablePratik">
            <tr ><td>First name</td><td>Last name</td><td>File name</td><td>File upload time</td><td>File update time</td><td>File description</td><td>File download</td><td>File update</td><td>File delete</td></tr>
      <%
         
          try
          {
         ServletContext context = getServletContext();
        Object MessageObject;
        String Message;
        String username=session.getAttribute("UserName").toString();
        MessageObject=session.getAttribute("Message");
         context.log("MessageObject:"+MessageObject);
       System.out.println("MessageObject:"+MessageObject);
       
        
        if(MessageObject!=null)
        {
           
            if(MessageObject.equals("File deleted successfully"))
            {
                 out.println("<script type=\"text/javascript\">");
                 out.println("alert('Files deleted successfully!');");
                 out.println("</script>");
            }
           else
           {
               
                 out.println("<script type=\"text/javascript\">");
                 out.println("alert('Files updated successfully!');");
                 out.println("</script>");        
           
           }
           session.removeAttribute("Message");
        }          

       
       context.log("UserName:"+username);
       ArrayList<DatabaseFilePojo> pojo=new ArrayList<DatabaseFilePojo>();
       ShowInformationOfFile info=new ShowInformationOfFile();
       pojo=info.fetchData(username);
       
       
       context.log("pojo.size()"+pojo.size());
        System.out.println("pojo.size()"+pojo.size());
       
       for(int i=0;i<pojo.size();i++)
        {
          context.log("Inside loop");
           DatabaseFilePojo object=new DatabaseFilePojo();   
           object=pojo.get(i);

         context.log("object is"+object);

            %>
            <tr id="1">
                    <td class="nr">
                       <%context.log("object.getFirstName()"+object.getFirstName());%> 
                       <%System.out.println("object.getFirstName()"+object.getFirstName());%> 
                      <%=object.getFirstName()%>
                     
                      </td>
                      
                      <td>
                      <%System.out.println("object.getLastName()"+object.getLastName());%> 
                      <%context.log("object.getLastName()"+object.getLastName());%>    
                      <%= object.getLastName()%>
                      </td>
                      
                      <td>
                      <%System.out.println("object.getFileName()"+object.getFileName());%>    
                      <%context.log("object.getFileName()"+object.getFileName());%>
                          <%= object.getFileName()%>
                    
                      </td>
                      
                      <td>
                      <%System.out.println("object.getFileUploadTime()"+object.getFileUploadTime());%>     
                      <%context.log("object.getFileUploadTime()"+object.getFileUploadTime());%>    
                      <%= object.getFileUploadTime()%>
                      </td>
                      
                      <td>
                       <%System.out.println("object.getUpdateTime()"+object.getUpdateTime());%>     
                      <%context.log("object.getUpdateTime()"+object.getUpdateTime());%>        
                      <%= object.getUpdateTime()%>
                      </td>
                      <td>
                      <%System.out.println(" object.getFileDescription()"+ object.getFileDescription());%>     
                      <%context.log(" object.getFileDescription()"+ object.getFileDescription());%>     
                      <%= object.getFileDescription()%>
                     </td>
                     <td><a href="DownloadServlet?name=<%= object.getFileName()%>"><input type="button"   value="Download"/></a></td>
                     <td><form action="UpdateServlet" method="post" enctype="multipart/form-data"><input type="file" name="file"><input type="submit" value="Update"/></form></td>
                     <td><a href="DeleteServlet?name=<%= object.getFileName()%>"><input type="button"  value="Delete"/></a></td>
             
                     
                     
                </tr>
            <% 
        }
}
catch(Exception e)
{

e.printStackTrace();
System.out.println("Exception e is:"+e);
}
    %>
</table>

<style>
a {text-decoration: none;}


</style>

<script>
<c:out value="${itemTitle}"/>
</script>
    </body>
</html>
