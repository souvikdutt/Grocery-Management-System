<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Add New Mahajan</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainOil">
		<form class="box" action="OilMahajanAddChcek.jsp" method="post" autocomplete="off">
			<h1>ADD NEW MAHAJAN</h1>
            <span style="color:red;font-size: 15px"><%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			 <span style="color:green;font-size: 15px"><%=(request.getAttribute("success") == null) ? "" : request.getAttribute("success")%></span>
			<table>
				<tr>
					<td><span>Mahajan ID :</span></td>
					<td><input type="text" name="mid" placeholder="ID" required="required"></td>
				</tr>
				<tr>
					<td><span>Mahajan Name :</span></td>
					<td><input type="text" name="mname" placeholder="Mahajan Name" required="required"></td>
				</tr>
				<tr>
					<td><span>Total Bill :</span></td>
					<td><input type="text" name="total_bill" placeholder="Total Bill" value="0"></td>
				</tr>
				<tr>
					<td><span>Total Payment :</span></td>
					<td><input type="text" name="total_pay" placeholder="Total Pay" value="0"></td>
				</tr>
				<tr>
					<td><span>Total Dues :</span></td>
					<td><input type="text" name="total_due" placeholder="Total Due" value="0"></td>
				</tr>
			</table>
			<input type="submit" value="SAVE">
			<a href="WelcomeMahajanOil.jsp"><font size="3" color="red">HOME</a>
		</form>
	</div>
</body>
</html>