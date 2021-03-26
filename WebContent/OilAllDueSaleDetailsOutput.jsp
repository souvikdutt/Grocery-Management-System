<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Sell Details</title>
<link rel="stylesheet" href="css/oilBill_info.css">
</head>
<body>
	<div class="main">
		<div class="box" >
		
			
				<%	
					String cdate=request.getParameter("cdate");
					
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_Sumbag="select cdate,oil_id,category,sum(quantity) from cust_oil group by cdate,oil_id,category having cdate=? order by oil_id";
					String select_SumBill="select sum(total_bill) from oilcust_bill where cdate=?";
					String select_TotalBag="select sum(quantity) from cust_oil where cdate=?";
					String totalPayment="select sum(total_pay) from oilcust_bill where cdate=?";
					String totalDue="select sum(total_due) from oilcust_bill where cdate=?";
					
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					%>
					<div class="billHeader">
						<div>
							<lable>Selling date : </lable>
							<b><%=cdate %></b>
						</div>
							<% ps=cn.prepareStatement(select_TotalBag);
								ps.setString(1, cdate);
								rs=ps.executeQuery();
								if(rs.next()){
							%>
							<div>
								<font >Total quantity :</font>
								<font color="blue"><%=rs.getString(1)%></font>
							</div>
							<% } %>
					</div>
					<table >
					<tr>
						<td class="col">OIL ID</td>
						<td class="col">CATEGORY</td>
						<td class="col">TOTAL QUANTITY</td>
					</tr>
					<%
					try{
					ps=cn.prepareStatement(select_Sumbag);
					ps.setString(1, cdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
				
					<tr>
						<td ><%=rs.getString(2)%></td>
						<td ><%=rs.getString(3)%></td>
						<td ><%=rs.getString(4)%></td>
					</tr>
					
				<% } %>
				</table>
					<br>
				<% 
	
					ps=cn.prepareStatement(select_SumBill);
					ps.setString(1, cdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<h2>Total Sell Amount : &#8377;&nbsp;<%=rs.getString(1)%></h2>
				<% }%>
				
				<br>
				<% 
	
					ps=cn.prepareStatement(totalPayment);
					ps.setString(1, cdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<h2>Total Payment Received : &#8377;&nbsp;<%=rs.getString(1)%></h2>
				<% }%>
				<br>
				<% 
	
					ps=cn.prepareStatement(totalDue);
					ps.setString(1, cdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<h2>Total Due Amount : &#8377;&nbsp;<%=rs.getString(1)%></h2>
				<% }
					
					}
				catch(SQLException se){
					se.printStackTrace();
					}
				obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			
			<a href="OilWelcomeDueCust.jsp" class="gobackHome">Go Home</a>
		</div>
	</div>
</body>
</html>