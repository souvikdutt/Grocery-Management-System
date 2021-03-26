<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Page</title>
<link rel="stylesheet" href="css/mahajan.css">
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
        <div class="home">
            <span>Stock</span>
            <a href="RiceHome.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="RiceAdd.jsp"><img src="images/mahajan/AddRice.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="RiceAdd.jsp">Add New Rice</a>
                </div>
            </div>
            <div class="container">
                <a href="StockShowPerRice.jsp"><img src="images/mahajan/view2.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="StockShowPerRice.jsp">Check Rice</a>
                </div>
            </div>
            <div class="container">
                <a href="AllRiceDetailsOutput.jsp"><img src="images/mahajan/AllStock.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="AllRiceDetailsOutput.jsp">All Stock Details</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>