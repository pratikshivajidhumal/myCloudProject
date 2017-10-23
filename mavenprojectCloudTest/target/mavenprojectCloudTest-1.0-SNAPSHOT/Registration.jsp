<%-- 
    Document   : Registration
    Created on : Oct 17, 2017, 8:11:52 PM
    Author     : pratik Dhumal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html><body>
        <header> <link rel="stylesheet" href="LoginCss.css"></header>
        
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
      <form class="login-form" action="RegistrationServlet" method="post">
      <input type="text" placeholder="First name"  name="firstName"  required/>
      <input type="text" placeholder="Last name" name="lastName"  required/>
      <input type="text" placeholder="User name" name="userName"  required/>
         <input type="password" placeholder="password" name="password"  required/>
      <input type="text" placeholder="email" name="email"  required/>
      <button>Register</button>      
      
    </form>
  </div>
</div>
   <script type="text/javascript">
    function validateForm()
    {
        
        if (firstName==null || firstName=="",lastName==null ||lastName =="",userName==null ||userName =="",password==null ||password =="",email==null ||email =="")
        {
            alert("All fields are mendatory");
            return false;
        }
    }
    
    </script>
    </body>
    </html>
    
