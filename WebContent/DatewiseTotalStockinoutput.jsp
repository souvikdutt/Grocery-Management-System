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
					PreparedStatement ps=null;
					ResultSet rs=null;
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();

					String sdate=request.getParameter("sdate");
					String select_TotalBag="select sum(nofbag) from mahajan_rice where sdate=?";
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
								<font color="black">Total bag :</font>
								<font color="blue"><%=rs.getString(1)%>&nbsp;bags</font>
							</div>
						</div>
					<% } %>
					<table >
					<tr>
						<td class="col">RICE ID</td>
						<td class="col">BAG</td>
						<td class="col">TOTAL NO OF BAG</td>
					</tr>
					<tr>
					<%
					String select_Sumbag="select sdate,rice_id,bag,sum(nofbag) from mahajan_rice group by sdate,rice_id,bag having sdate=? order by rice_id";
					String select_SumBill="select sum(total_bill) from mahajan_billing where sdate=?";
					
					try{
					ps=cn.prepareStatement(select_Sumbag);
					ps.setString(1, sdate);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<td ><%=rs.getString(2)%></td>
					<td ><%=rs.getString(3)%>kg</td>
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
			
			<a href="WelcomeMahajan.jsp" class="gobackHome">Go Home</a>
		</div>
	</div>
</body>
</html>