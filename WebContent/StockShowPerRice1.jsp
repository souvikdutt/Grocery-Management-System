<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/AllRiceOutput.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //prevent back button after logout
		response.setHeader("Expires", "0"); //Proxies
		if(session.getAttribute("username")==null){
			response.sendRedirect("LogIn.jsp");
		}
	%>
		<div class="navbar">
			<a href="WelcomeStock.jsp" class="logo">STOCK DETAILS</a>
	        <ul class="menu">
	            <li><a href="Home.jsp" class="active">Home</a></li>
	        </ul>
		</div>
		<div class="box" >
			<table>
				<tr>
					<td class="col">RICE ID</td>
					<td class="col">BAG(1/5/10/25/50/60)KG</td>
					<td class="col">NO OF BAG</td>
				</tr>
				<%	
					String rice_id=request.getParameter("rice_id");	
				
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_stockRice="select * from stock_rice where rice_id=?";
					
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					ps=cn.prepareStatement(select_stockRice);
					ps.setString(1, rice_id);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2) %>&nbsp;kg</td>
					<td ><%=rs.getString(3) %></td>
					</tr>
				<% } 
					}
				catch(SQLException se){
					se.printStackTrace();
					}
				obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			</table>	
			<div class="goHome">
				<a href="WelcomeStock.jsp"><button>Go Back &#x276F;</button></a>
			</div>
		</div>
		<div class="footer">
		  <p>&#xa9; Designed by: Souvik Dutta & Sayan Jashu</p>
		</div>
</body>
</html>