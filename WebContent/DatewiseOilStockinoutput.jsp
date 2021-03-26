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
			
			
					<%	
					PreparedStatement ps=null;
					ResultSet rs=null;
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();

					String sdate=request.getParameter("sdate");
					String select_TotalBag="select sum(quantity) from mahajan_oil where sdate=?";
					ps=cn.prepareStatement(select_TotalBag);
					ps.setString(1, sdate);
					rs=ps.executeQuery();
					if(rs.next()){
					%>
						<div class="billHeader">
							<div>
								<lable>Date : </lable>
								<b><%=sdate %></b>
							</div>
							<div>
								<font color="black">Total quantity :</font>
								<font color="blue"><%=rs.getString(1)%>&nbsp;</font>
							</div>
						</div>
					<% } %>
					<table >
					<tr>
						<td class="col">OIL ID</td>
						<td class="col">CATEGORY</td>
						<td class="col">QUANTITY</td>
					</tr>
					<tr>
					<%
					String select_Sumbag="select sdate,oil_id,category,sum(quantity) from mahajan_oil group by sdate,oil_id,category having sdate=? order by oil_id";
					String select_SumBill="select sum(total_bill) from oilmahajan_billing where sdate=?";
					
					try{
					ps=cn.prepareStatement(select_Sumbag);
					ps.setString(1, sdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<td ><%=rs.getString(2)%></td>
					<td ><%=rs.getString(3)%></td>
					<td ><%=rs.getString(4)%></td>
					</tr>
					
				<% } %>
				</table>
					
				<% 
					}
				catch(SQLException se){
					se.printStackTrace();
					}
					obj.releaseConnectionSourcePS(rs, ps, cn);%>
			
			<a href="WelcomeMahajanOil.jsp" class="gobackHome">Go Home</a>
		</div>
	</div>
</body>
</html>