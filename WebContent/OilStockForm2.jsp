<%@page import="master.DAO.StockOilDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Login Page</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainOil">
		<form class="box" action="OilStockForm2CheckOilId" method="post"
			autocomplete="off">
			<%
				String sdate = request.getParameter("sdate");
				String mid = request.getParameter("mid");
			%>
			<span style="color: red; font-size: 15px"> <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<span style="color: green; font-size: 15px"> <%=(request.getAttribute("success") == null) ? "" : request.getAttribute("success")%></span>
			<table>
				<tr>
					<td><span>Date :</span></td>
					<td><input type="text" name="sdate" value="<%=sdate%>"></td>
				</tr>
				<tr>
					<td><span>Mahajan ID :</span></td>
					<td><input type="text" name="mid" value="<%=mid%>"></td>
				</tr>
				<tr>
					<td><span>Oil ID :</span></td>
					<td>
						<%
							StockOilDAO obj=new StockOilDAO();
							ArrayList arr = new ArrayList();
							arr = obj.getOilID();
							Iterator itr = arr.iterator();
						%> <select id="rice_id" name="oil_id">
							<%
								while (itr.hasNext()) {
									String x=(String) itr.next();
							%>
							<option value="<%=x%>"><%=x%></option>
							<%
								}
							%>
					</select>
					</td>
				</tr>
				<tr>
					<td><span>Category :</span></td>
					<td><select id="weightOfbag" name="category" required="required">
							<option value="Tin">Tin</option>
							<option value="Peti">Peti</option>
						</select></td>
				</tr>
				<tr>
					<td><span>Quantity :</span></td>
					<td><input type="text" name="quan" placeholder="Quantity" required="required"></td>
				</tr>
				<tr>
					<td><span>Price :</span></td>
					<td><input type="text" name="price" placeholder="price" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="ADD">
		</form>

		<form class="box2" action="OilStockForm3.jsp" method="post">
			<input type="text" name="sdate" value="<%=sdate%>"> <input
				type="text" name="mid" value="<%=mid%>"> <input
				type="submit" value="BILL INSERT">
		</form>

	</div>
</body>
</html>