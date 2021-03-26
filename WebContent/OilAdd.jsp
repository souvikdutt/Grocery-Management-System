<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rice Entry</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainOil">
		<form class="box" action="OilSAddCheckOilId" method="post" autocomplete="on">
			<h1>NEW OIL ENTRY</h1>
			<span style="color:red;font-size: 15px"><%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<span style="color:green;font-size: 15px"><%=(request.getAttribute("saved") == null) ? "" : request.getAttribute("saved")%></span>
			<table>
				<tr>
					<td><label>Oil ID :</label></td>
					<td><input type="text" name="oil_id" placeholder="Oil ID" required="required"></td>
				</tr> 
				<tr>
					<td><label>Category :</label></td>
					<td><select id="weightOfbag" name="category" required="required">
				          <option value="Tin">Tin</option>
				          <option value="Peti">Peti</option>
				         </select>
				      </td>
				 </tr>
				 <tr>
					 <td><label>Quantity :</label></td>
					 <td><input type="text" name="quan" placeholder="Quantity" required="required"></td>
				 </tr>
				</table>	
				<input type="submit" value="SAVE">
				<a href="WelcomeStockOil.jsp">Home</a>
			
		</form>
	</div>
</body>
</html>