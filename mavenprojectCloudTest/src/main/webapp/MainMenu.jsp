<%-- 
    Document   : MainMenu
    Created on : Oct 17, 2017, 8:46:42 PM
    Author     : pratik Dhumal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <header><h1>Welcome back&nbsp;&nbsp;<%=session.getAttribute("UserName")%></h1>
        <%
          
          Object MessageObject;
          String s=session.getAttribute("UserName").toString();
          session.setAttribute("UserName", s);
          MessageObject=session.getAttribute("Message");
       
        if(MessageObject!=null)
        {
           out.println("<script type=\"text/javascript\">");
           out.println("alert('Files uploaded successfully!');");
           out.println("</script>");
           session.removeAttribute("Message"); 
        }  
          

         %>
        
        <h3><a href="Login.jsp">logout</a></h3>
    </header>
        
    <form id="myform"  action="MainMenuServlet"  onsubmit="return myFunction();" method="post" enctype="multipart/form-data">
   
    <label for="file">Choose file to upload</label>
    <input type="file" id="file" name="file" multiple>
    <input type="submit" value="Submit" id="btnSubmit"/>
     
    
    
   
</form>
       <br> 
       <form id="FetchData" action="showMyListOfFiles.jsp" method="post">
           <input type="submit" value="View my files">
        </form>
    
    <script> 
    function myFunction() 
    {
        
        var fileUpload = document.getElementById('file');
        var flag=false;
       
        if (typeof (fileUpload.files) !== "undefined") 
        {
            for( var i=0;i<fileUpload.files.length;i++)
            {
               var size = fileUpload.files[i].size / 1024;
               var maxsize=10*1024;
               
               if(size>maxsize)
               {
                   alert("please reload files size of some files greater than 10 MB");
                   document.getElementById("myform").reset();
                   flag=false;

                   
               }
               else
               {
                    flag=true;
                  
                 
               }
               
            }
        } else 
        {
            
            alert("This browser does not support HTML5.");
        }
        return flag;
    }
    
    
    </script>
    
    
</body>
    
      <style>
        
body {
  background: #76b852; 
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
</style>
    
</html>
