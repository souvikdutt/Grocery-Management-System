<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/oilBill_info.css">
</head>
<body>
	<div class="main">
		<div class="box" >
		<center>
			<table >
				<%	
					String cash_date=request.getParameter("cash_date");%>
					<tr>
						<td class="col">DATE</td>
						<td class="col">OIL ID</td>
						<td class="col">CATEGORY</td>
						<td class="col">QUANTITY</td>
					</tr>
					<tr>
					<%
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_Sumbag="select cash_date,oil_id,category,sum(quantity) from cash_oil group by cash_date,oil_id,category having cash_date=? order by oil_id";
					String select_SumBill="select sum(total_bill) from oilcash_bill where cash_date=?";
					String select_TotalBag="select sum(quantity) from cash_oil where cash_date=?";
							
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					ps=cn.prepareStatement(select_Sumbag);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2)%></td>
					<td ><%=rs.getString(3)%></td>
					<td ><%=rs.getString(4)%></td>
					</tr>
					
				<% } 
					
					ps=cn.prepareStatement(select_TotalBag);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					if(rs.next()){
				%>
					<tr>
					<td ></td>
					<td ></td>
					<td ><font color="red" size="5">Total bag :</font></td>
					<td ><font color="blue" size="5"><%=rs.getString(1)%>pcs</font></td>
					</tr>
					
				<% } %>
				</table>
					<br>
					<br>
				<% 
	
					ps=cn.prepareStatement(select_SumBill);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<h1>Total cash Amount : &#8377;<%=rs.getString(1)%></h1>
				<% }
					
					}
				catch(SQLException se){
					se.printStackTrace();
					}
				obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			<br>
			<br>
			<br>
			<a href="OilWelcomeCash.jsp">click Home</a>
			</center>
		</div>
	</div>
</body>
</html>