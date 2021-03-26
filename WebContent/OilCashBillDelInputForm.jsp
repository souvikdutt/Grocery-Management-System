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
	<div class="main">
		<form class="box" action="OilCashDelBidDateCheck" method="post" autocomplete="off">
			<span style="color:red;font-size: 15px">
			<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<input class="date" type="date" name="cash_date" placeholder="Date" required="required">
			<input type="text" name="billno" placeholder="Bill No" required="required"> <input
				type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>