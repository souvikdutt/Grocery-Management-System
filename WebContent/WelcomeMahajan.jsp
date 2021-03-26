<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Mahajan Page</title>
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
            <span>Mahajan</span>
            
            <span style="color: green; font-size: 15px"> <%=(request.getAttribute("success") == null) ? "" : request.getAttribute("success")%></span>
			<span style="color: green; font-size: 15px"> <%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            
            <a href="RiceHome.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="StockForm1.jsp"><img src="images/mahajan/stockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="StockForm1.jsp">New Stock In</a>
                </div>
            </div>
            <div class="container">
                <a href="ViewPerDateForm1.jsp"><img src="images/mahajan/view2.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="ViewPerDateForm1.jsp">View/Edit Stock In Bills</a>
                </div>
            </div>
            <div class="container">
                <a href="DatewiseTotalStockIn.jsp"><img src="images/mahajan/allStockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DatewiseTotalStockIn.jsp">Check All Stock In</a>
                </div>
            </div>
            <div class="container">
                <a href="MdetailsInputForm.jsp"><img src="images/mahajan/viewMahajan.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="MdetailsInputForm.jsp">View Mahajan Details</a>
                </div>
            </div>
            <div class="container">
                <a href="ExPaymentForm.jsp"><img src="images/mahajan/payment.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="ExPaymentForm.jsp">Payment to Mahajan</a>
                </div>
            </div>
            <div class="container">
                <a href="MahajanAdd.jsp"><img src="images/mahajan/addNew.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="MahajanAdd.jsp">Add Mahajan</a>
                </div>
            </div>
            <div class="container">
                <a href="MidDeleteInputForm.jsp"><img src="images/mahajan/delete.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="MidDeleteInputForm.jsp">Delete Mahajan</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>