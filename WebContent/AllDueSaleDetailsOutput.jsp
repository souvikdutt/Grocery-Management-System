<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Sell Details</title>
<link rel="stylesheet" href="css/billingInfo.css">
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
		<div class="box" >
		
			
				<%	
					String cdate=request.getParameter("cdate");
					
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_Sumbag="select cdate,rice_id,bag,sum(nofbag) from cust_rice group by cdate,rice_id,bag having cdate=? order by rice_id";
					String select_SumBill="select sum(total_bill) from cust_bill where cdate=?";
					String select_TotalBag="select sum(nofbag) from cust_rice where cdate=?";
					String totalPayment="select sum(total_pay) from cust_bill where cdate=?";
					String totalDue="select sum(total_due) from cust_bill where cdate=?";
					
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
								<font >Total bag :</font>
								<font color="blue"><%=rs.getString(1)%>pcs</font>
							</div>
							<% } %>
					</div>
					<table >
					<tr>
						<td class="col">RICE ID</td>
						<td class="col">BAG</td>
						<td class="col">TOTAL NO OF BAG</td>
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
						<td ><%=rs.getString(3)%>kg</td>
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
			
			<a href="WelcomeDueCust.jsp" class="gobackHome">Go Home</a>
		</div>
	</div>
</body>
</html>