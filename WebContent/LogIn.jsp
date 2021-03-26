<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="stylesheet" href="css/admin_login.css">
    </head>
    <body>
     <div class="main">
        <form class="box" action="LogInCheck" method="post" autocomplete="off">
        <span style="color:red;font-size: 15px">
            <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
            <img src="images/admin_logo.png" alt="Admin" height="125px" style="padding: 10px">
            <input type="text" name="gmail" placeholder="gmail">
            <input type="password" name="pass" placeholder="password">
            <input type="submit"  value="Login">
            <a href="SignUp.jsp"><font color="red">Create Account</font></a>
        </form>
     </div>
    </body>
</html>