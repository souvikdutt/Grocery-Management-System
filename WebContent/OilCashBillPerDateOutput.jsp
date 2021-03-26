<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/billingInfo.css">
</head>
<body>
	<div class="main">
		<div class="box" >
		
			<table>
				<tr>
					<td class="col">DATE</td>
					<td class="col">BILL NO</td>
					<td class="col">OIL ID</td>
					<td class="col">CATEGORY</td>
					<td class="col">QUANTITY</td>
					<td class="col">PRICE</td>
				</tr>
				<%	
					String cash_date=request.getParameter("cash_date");
					String billno=request.getParameter("billno");
	
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_CashRice="select * from cash_oil where cash_date=? and billno=? ";
					String select_CashBill="select * from oilcash_bill where cash_date=? and billno=?";
							
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					ps=cn.prepareStatement(select_CashRice);
					ps.setString(1, cash_date);
					ps.setString(2, billno);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2) %></td>
					<td ><%=rs.getString(3) %></td>
					<td ><%=rs.getString(4) %></td>
					<td ><%=rs.getString(5) %></td>
					<td >&#8377;<%=rs.getString(6) %></td>
					</tr>
				<% } 
					}
				catch(SQLException se){
					se.printStackTrace();
					}%>
			</table>
			<table>
				<tr>
					<td class="col">DATE</td>
					<td class="col">BILL NO</td>
					<td class="col">TOTAL BILL(TO DATE)</td>
				</tr>
				<%	
					try{
					ps=cn.prepareStatement(select_CashBill);
					ps.setString(1, cash_date);
					ps.setString(2, billno);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2) %></td>
					<td >&#8377;<%=rs.getString(3) %></td>
					</tr>
				<% } 
					}
				catch(SQLException se){
					se.printStackTrace();
					}
					obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			</table>
			<a href="OilCashBillDelInputForm.jsp"><input class="mahajan_button" type="button" value="DELETE"></a>
			
			<a href="OilWelcomeCash.jsp">click Home</a>
			
		</div>
	</div>
</body>
</html>