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
	<div class="mainOil">
		<form class="box" action="CashGeneratedBill.jsp" method="post" autocomplete="off">
			<input class="date" type="date" name="cash_date" placeholder="Date" required="required">
			<input type="text" name="billno" placeholder="Bill No" required="required">
			 <input	type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>