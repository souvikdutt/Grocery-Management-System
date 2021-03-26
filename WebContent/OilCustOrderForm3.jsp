<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Log.css">
<script type="text/javascript">
		
		function CalDue() {
			
			var total_bill=document.getElementById("total_bill").value;
			var payment=document.getElementById("payment").value;
			
			var due=total_bill-payment;
			document.getElementById("due").value=due;
			
		}
		
</script>
</head>
<body>
<div class="mainOil">
		<form class="box" action="OilCustomerBillAddServe" method="post" autocomplete="off">
		<%
			String cdate=request.getParameter("cdate");
			String cid=request.getParameter("cid");
			
			ConnectionFactory obj=new ConnectionFactory();
			Connection cn=obj.getCon();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String total_bill="select sum(price) from cust_oil where cdate=? and cid=?";
			
			String sbill=null;
			
			try{
				ps=cn.prepareStatement(total_bill);
				ps.setString(1, cdate);
				ps.setString(2, cid);
				rs=ps.executeQuery();
				if(rs.next())
					sbill=rs.getString(1);
			}catch(SQLException se){
				se.printStackTrace();
			}
			obj.releaseConnectionSourcePS(rs, ps, cn);
		%>
		<table>
			<tr>
				<td>Date :</td>
				<td><input type="text" name="cdate" value="<%=cdate %>"></td>
			</tr>
			<tr>
				<td>Customer ID :</td>
				<td><input type="text" name="cid" value="<%=cid %>"></td>
			</tr>
			<tr>
				<td>Total Amount :</td>
				<td><input type="text" name="total_bill" placeholder="Total Bill" value="<%=sbill %>" id="total_bill" required="required"></td>
			</tr>
			<tr>
				<td>Payment :</td>
				<td><input type="text" name="payment" placeholder="Payment of Customer" id="payment" required="required"></td>
			</tr>
			<tr>
				<td>Remain Due :</td>
				<td><input type="text" name="due" placeholder="Due" id="due" onclick="CalDue();" required="required"></td>
			</tr>
		</table>   
			<input	type="submit" value="SUBMIT AND EXIT"> 
		</form>
</div>

</body>
</html>