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
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //prevent back button after logout
		response.setHeader("Expires", "0"); //Proxies
		if(session.getAttribute("username")==null){
			response.sendRedirect("LogIn.jsp");
		}
	%>
<div class="main">
		<form class="box" action="MahajanBillAddServe" method="post" autocomplete="off">
		<%
			String sdate=request.getParameter("sdate");
			String mid=request.getParameter("mid");
			
			ConnectionFactory obj=new ConnectionFactory();
			Connection cn=obj.getCon();
			
			PreparedStatement ps=null;
			ResultSet rs=null;
			String total_bill="select sum(price) from mahajan_rice where sdate=? and mid=?";
			
			String sbill=null;
			
			try{
				ps=cn.prepareStatement(total_bill);
				ps.setString(1, sdate);
				ps.setString(2, mid);
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
				<td><span>Date :</span></td>
				<td><input type="text" name="sdate" value="<%=sdate %>"></td>
			</tr>
			<tr>
				<td><span>Mahajan ID :</span></td>
				<td><input type="text" name="mid" value="<%=mid %>"></td>
			</tr>
			<tr>
				<td><span>Total Amount :</span></td>
				<td><input type="text" name="total_bill" value="<%=sbill %>" id="total_bill" required="required"></td>
			</tr>
			<tr>
				<td><span>Payment of :</span></td>
				<td><input type="text" name="payment" placeholder="Payment To Mahajan" id="payment" required="required"></td>
			</tr>
			<tr>
				<td><span>Remain Due :</span></td>
				<td><input type="text" name="due" placeholder="Due" id="due" onclick="CalDue();" required="required"></td>
			</tr>
		</table>
		<input	type="submit" value="SUBMIT AND EXIT"> 
		</form>

</body>
</html>