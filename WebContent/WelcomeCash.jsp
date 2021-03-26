<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cash Customers</title>
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
            <span>Cash Customer</span>
            <span style="color:green;font-size: 15px">
		    <%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            <a href="RiceHome.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="CashOrderForm1.jsp"><img src="images/mahajan/cashBill.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CashOrderForm1.jsp">Cash Bill Entry</a>
                </div>
            </div>
            <div class="container">
                <a href="CashBillPerDateInput.jsp"><img src="images/mahajan/view2.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CashBillPerDateInput.jsp">View Bill</a>
                </div>
            </div>
            <div class="container">
                <a href="DatewiseBagCash.jsp"><img src="images/mahajan/AllStock.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DatewiseBagCash.jsp">Total Cash Sell</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>