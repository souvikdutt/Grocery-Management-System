<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Log.css">
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
		<form class="box" action="CustomerAddCheck.jsp" method="post" autocomplete="off">
			<h1>CUSTOMER DETAILS</h1>
            <span style="color:red;font-size: 15px"><%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			  <span style="color:green;font-size: 15px"><%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
			<table>
				<tr>
					<td><span>Customer ID :</span></td>
					<td><input type="text" name="cid" placeholder="Customer ID" required="required"></td>
				</tr>
				<tr>
					<td><span>Customer Name :</span></td>
					<td><input type="text" name="cname" placeholder="Customer Name" required="required"></td>
				</tr>
				<tr>
					<td><span>Phone No :</span></td>
					<td><input type="text" name="phno" placeholder="Phone Number" required="required"></td>
				</tr>
				<tr>
					<td><span>Total Bill :</span></td>
					<td><input type="text" name="total_bill" placeholder="Total Bill" value="0" required="required"></td>
				</tr>
				<tr>
					<td><span>Total Payment :</span></td>
					<td><input type="text" name="total_pay" placeholder="Total Pay" value="0" required="required"></td>
				</tr>
				<tr>
					<td><span>Total Dues :</span></td>
					<td><input type="text" name="total_due" placeholder="Total Due" value="0" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="SAVE">
			<a href="WelcomeDueCust.jsp"><font size="3" color="red">HOME</a>
		</form>
	</div>
</body>
</html>