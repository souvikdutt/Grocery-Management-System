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
	<div class="mainOil">
        <div class="home">
            <span>Due Customer</span>
            <span style="color:green;font-size: 15px">
			<%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            <a href="HomeOil.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="OilCustOrderForm1.jsp"><img src="images/customers/OrderEntry.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilCustOrderForm1.jsp">New Bill Entry</a>
                </div>
            </div>
            <div class="container">
                <a href="OilInputCustPerDate.jsp"><img src="images/customers/ViewBill.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilInputCustPerDate.jsp">View Bill</a>
                </div>
            </div>
            <div class="container">
                <a href="OilAllDueSaleDetailsInput.jsp"><img src="images/mahajan/allStockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilAllDueSaleDetailsInput.jsp">Due Sell Details</a>
                </div>
            </div>
            <div class="container">
                <a href="OilCustDetailsInputForm.jsp"><img src="images/mahajan/viewMahajan.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilCustDetailsInputForm.jsp">Customer Details</a>
                </div>
            </div>
            <div class="container">
                <a href="OilExPaymentCust.jsp"><img src="images/mahajan/payment.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilExPaymentCust.jsp">Others Payment</a>
                </div>
            </div>
            <div class="container">
                <a href="OilCustomerAdd.jsp"><img src="images/customers/AddCustomer.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilCustomerAdd.jsp">Add Customer</a>
                </div>
            </div>
            <div class="container">
                <a href="OilCustIdDeleteInputForm.jsp"><img src="images/customers/delelteCust.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="OilCustIdDeleteInputForm.jsp">Delete Customer</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>