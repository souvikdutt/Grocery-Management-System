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
		<form class="box" action="OilCashBillAddServe" method="post">
		<%
			String cash_date=request.getParameter("cash_date");
			String billno=request.getParameter("billno");
			
			ConnectionFactory obj=new ConnectionFactory();
			Connection cn=obj.getCon();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String total_bill="select sum(price) from cash_oil where cash_date=? and billno=?";
			
			String sbill=null;
			
			try{
				ps=cn.prepareStatement(total_bill);
				ps.setString(1, cash_date);
				ps.setString(2, billno);
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
				<td><input type="text" name="cash_date" value="<%=cash_date %>"></td>
			</tr>
			<tr>
				<td>Bill No :</td>
				<td><input type="text" name="billno" value="<%=billno %>"></td>
			</tr>
			<tr>
				<td>Total Amount :</td>
				<td><input type="text" name="total_bill" placeholder="Total Bill" value="<%=sbill %>" id="total_bill"></td>
			</tr>
		</table>   
			<input	type="submit" value="SUBMIT AND EXIT"> 
		</form>

</body>
</html>