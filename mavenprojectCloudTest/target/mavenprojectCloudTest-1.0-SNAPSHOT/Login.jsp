<%-- 
    Document   : Login
    Created on : Oct 17, 2017, 7:43:45 PM
    Author     : pratik Dhumal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html><body>
        <header> <link rel="stylesheet" href="LoginCss.css"></header>
        
<div class="login-page">
  <div class="form">
    <form class="register-form">
      <input type="text" placeholder="username"/>
      <input type="password" placeholder="password" />
      <input type="text" placeholder="email address"/>
      <button>create</button>
      <p class="message">Already have an account <a href="#">Sign In</a></p>
    </form>
      <form class="login-form" action="LoginServlet" method="post">
      <input type="text" placeholder="username"  name="username"  required/>
      <input type="password" placeholder="password" name="password"  required/>
      <button>login</button>      
      <p class="message">New user? <a href="Registration.jsp">open an account</a></p>
    </form>
  </div>
</div>
   <script type="text/javascript">
    function validateForm()
    {
        
        if (username==null || username=="",password==null ||password =="")
        {
            alert("User name and password can not be empty");
            return false;
        }
    }
    
    </script>
    </body>
    </html>
    
