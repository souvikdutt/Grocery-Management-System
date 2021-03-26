<%@page import="master.DAO.StockRiceDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Login Page</title>
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
		<form class="box" action="StockForm2CheckRiceId" method="post"
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
					<td><span>Rice ID :</span></td>
					<td>
						<%
							StockRiceDAO obj = new StockRiceDAO();
							ArrayList arr = new ArrayList();
							arr = obj.getRiceId();
							Iterator itr = arr.iterator();
						%> <select id="rice_id" name="rice_id">
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
					<td><span>Bag(Weight) :</span></td>
					<td><select id="weightOfbag" name="bag" required="required">
							<option value="1">1 KG</option>
							<option value="5">5 KG</option>
							<option value="10">10 KG</option>
							<option value="25">25 KG</option>
							<option value="50">50 KG</option>
							<option value="60">60 KG</option>
					</select></td>
				</tr>
				<tr>
					<td><span>No. of Bag :</span></td>
					<td><input type="text" name="nofbag" placeholder="No Of Bags" required="required"></td>
				</tr>
				<tr>
					<td><span>Price :</span></td>
					<td><input type="text" name="price" placeholder="price" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="ADD">
		</form>

		<form class="box2" action="StockForm3.jsp" method="post">
			<input type="text" name="sdate" value="<%=sdate%>"> <input
				type="text" name="mid" value="<%=mid%>"> <input
				type="submit" value="BILL INSERT">
		</form>

	</div>
</body>
</html>