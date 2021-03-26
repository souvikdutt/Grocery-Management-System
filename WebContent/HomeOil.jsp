<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oil Management</title>
    <link rel="stylesheet" href="css/home1.css">
</head>
<body>
    <div class="main">
        <div class="nav_bar">
            <a href="HomeOil.jsp" class="home">Oilpage</a>
            <div class="links">
                <a href="WelcomeMahajanOil.jsp">Mahajan</a>
                <div class="dropdown">
                    <a href="#" dropbtn>Customers</a>
                    <div class="dropdown-content">
                        <a href="OilWelcomeDueCust.jsp">Due Customers</a>
                        <a href="OilWelcomeCash.jsp">Cash Customers</a>
                    </div>
                </div>
                <a href="WelcomeStockOil.jsp">Stocks</a>
            </div>
            <div class="logout">
                <a href="Home.jsp">Home</a>
            </div>
        </div>
        <div class="details">
            <span><h1>Welcome Admin !</h1></span>
            <p>It is the primary tool for you to work with your online store. Here you can manage products and orders, stocks, interact with your lenders, track transactions, generate bills and do much more.</p>
            <a href="AllOilDetailsOutput.jsp">Check Stocks</a>
        </div>
        <div class="footer">
            <span>Designed by: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>