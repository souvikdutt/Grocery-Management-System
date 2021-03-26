<%@page import="master.DAO.StockRiceDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/Log.css">
<script type="text/javascript">
		
		function totalweight() {
			
			var bagkg=document.getElementById("weightOfbag").value;
			var nofbag=document.getElementById("nofbag").value;
			
			var weight=bagkg*nofbag;
			var quinweight=weight/100;
			document.getElementById("totalWeight").value=quinweight;
			
		}
		
		function priceCal() {
			
			var totalweight=document.getElementById("totalWeight").value;
			var rate_quin=document.getElementById("rate_quin").value;
			
			var pri=totalweight*rate_quin;
			document.getElementById("price").value=pri;
		}
	
</script>
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
		<form class="box"  action="CashOrderForm2CheckRiceId" method="post" autocomplete="off">
		<%
			String cash_date=request.getParameter("cash_date");
			String billno=request.getParameter("billno");
		%>
		<span style="color:red;font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
		<span style="color:green;font-size: 15px">
		<%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
		<table>
			<tr>
				<td>Date :</td>
				<td><input type="text" name="cash_date" value="<%=cash_date %>"></td>
			</tr>
			<tr>
				<td>Bill No :</td>
				<td><input type="text" name="billno" value="<%=billno %>"></td>
			</tr>
			<tr>
					<td><span>Rice ID :</span></td>
					<td><%
						StockRiceDAO obj = new StockRiceDAO();
						ArrayList arr=new ArrayList();
						arr=obj.getRiceId();
						Iterator itr=arr.iterator();
							%>
							<select id="rice_id" name="rice_id"><%
							while(itr.hasNext()){
								String x=(String) itr.next();
							%>
						          <option value="<%=x %>"><%=x %></option>
							<%}%> </select>
					</td>
			</tr>
			<tr>
					<td><span>Bag(Weight) :</span></td>
					<td><select id="weightOfbag" name="bag">
				          <option value="1">1 KG</option>
				          <option value="5">5 KG</option>
				          <option value="10">10 KG</option>
				          <option value="25">25 KG</option>
				          <option value="50">50 KG</option>
				          <option value="60">60 KG</option>
				        </select>
				      </td>
			</tr>
			<tr>
				<td>No. of Bag :</td>
				<td><input type="text" name="nofbag" placeholder="No Of Bags" id="nofbag" required="required"></td>
			</tr>
			<tr>
				<td>Total Weight(Quintal) :</td>
				<td><input type="text" name="total_weight" placeholder="Total weight" id="totalWeight" onclick="totalweight();" required="required"></td>
			</tr>
			<tr>
				<td>Rate/Quintal :</td>
				<td><input type="text" name="rate_quin" placeholder="Rate/quintal" id="rate_quin" required="required"></td>
			</tr>
			<tr>
				<td>Price :</td>
				<td><input type="text" name="price" placeholder="price" id="price" onclick="priceCal();" required="required"></td>
			</tr>
		</table>   
			<input	type="submit" value="ADD"> 
		</form>
		
		<form class="box2" action="CashOrderForm3.jsp" method="post">
			<input type="text" name="cash_date" value="<%=cash_date %>">
			<input type="text" name="billno" value="<%=billno %>">
			<input	type="submit" value="BILL INSERT"> 
		</form>
		
	</div>
</body>
</html>