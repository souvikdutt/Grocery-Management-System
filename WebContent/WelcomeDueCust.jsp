<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Due Customer Page</title>
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
            <span>Due Customer</span>
            <span style="color:green;font-size: 15px">
			<%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            <a href="RiceHome.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="CustOrderForm1.jsp"><img src="images/customers/OrderEntry.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CustOrderForm1.jsp">New Bill Entry</a>
                </div>
            </div>
            <div class="container">
                <a href="InputCustPerDate.jsp"><img src="images/customers/ViewBill.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="InputCustPerDate.jsp">View Bill</a>
                </div>
            </div>
            <div class="container">
                <a href="AllDueSaleDetailsInput.jsp"><img src="images/mahajan/allStockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="AllDueSaleDetailsInput.jsp">Due Sell Details</a>
                </div>
            </div>
            <div class="container">
                <a href="CustDetailsInputForm.jsp"><img src="images/mahajan/viewMahajan.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CustDetailsInputForm.jsp">Customer Details</a>
                </div>
            </div>
            <div class="container">
                <a href="ExPaymentCust.jsp"><img src="images/mahajan/payment.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="ExPaymentCust.jsp">Others Payment</a>
                </div>
            </div>
            <div class="container">
                <a href="CustomerAdd.jsp"><img src="images/customers/AddCustomer.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CustomerAdd.jsp">Add Customer</a>
                </div>
            </div>
            <div class="container">
                <a href="CustIdDeleteInputForm.jsp"><img src="images/customers/delelteCust.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="CustIdDeleteInputForm.jsp">Delete Customer</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>