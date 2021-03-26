<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //prevent back button after logout
		response.setHeader("Expires", "0"); //Proxies
		if(session.getAttribute("username")==null){
			response.sendRedirect("LogIn.jsp");
		}
	%>

	<div class="main">
        <div class="nav_bar">
            <a href="Home.jsp" class="home">Homepage</a>
            <div class="links">
                <a href="RiceHome.jsp">Rice Management</a>
                <a href="HomeOil.jsp">Oil Management</a>
                <a href="#">Dal Management</a>
            </div>
            <form class="logout" action="Logout" method="post">
				<input type="submit" value="Logout">
		    </form>
        </div>
        <div class="details">
            <span><h1>Welcome Admin !</h1></span>
            <p>It is the primary tool for you to work with your online store. Here you can manage products and orders, stocks, interact with your lenders, track transactions, generate bills and do much more.</p>
            <a href="AllRiceDetailsOutput.jsp">Check Stocks</a>
        </div>
    </div>
</body>
</html>