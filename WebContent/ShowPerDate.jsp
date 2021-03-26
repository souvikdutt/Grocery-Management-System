<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Per Date</title>
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
					String sdate=request.getParameter("sdate");
					String mid=request.getParameter("mid");
					
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_mahajanRice="select * from mahajan_rice where sdate=? and mid=? ";
					String select_mahajanBilling="select * from mahajan_billing where sdate=? and mid=?";
					String select_Mname="select mname from mahajan_main where mid=?";
							
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					

					try{
						ps=cn.prepareStatement(select_Mname);
						ps.setString(1, mid);
						rs=ps.executeQuery();
						if(rs.next()){%>
							<div class="billHeader">
								<div>
								<lable>Mahajan ID : </lable>
								<b><%=mid %></b>
								</div>
								
								<div>
								<lable>Name : </lable>
								<b><%=rs.getString(1) %></b>
								</div>
								
								<div>
								<lable>Purchase date : </lable>
								<b><%=sdate %></b>
								</div>
							</div>
						<%}
					}catch(SQLException se){
						se.printStackTrace();
					}%>
					<table>
					<tr>
						<td class="col">RICE ID</td>
						<td class="col">BAG(1/5/10/25/50/60)KG</td>
						<td class="col">NO OF BAGS</td>
						<td class="col">PRICE</td>
					</tr>
				<%
					try{
					ps=cn.prepareStatement(select_mahajanRice);
					ps.setString(1, sdate);
					ps.setString(2, mid);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
				
					<tr>
						<td ><%=rs.getString(3) %></td>
						<td ><%=rs.getString(4) %>KG</td>
						<td ><%=rs.getString(5) %></td>
						<td >&#8377;<%=rs.getString(6) %></td>
					</tr>
				<% }
					}
				catch(SQLException se){
					se.printStackTrace();
					}%>
			</table>
			<table class="totalTable">
				<tr>
					<td class="col">TOTAL BILL(TO DATE)</td>
					<td class="col">PAYMENT(TO DATE)</td>
					<td class="col">DUES(TO DATE)</td>
				</tr>
				<%	
					try{
					ps=cn.prepareStatement(select_mahajanBilling);
					ps.setString(1, sdate);
					ps.setString(2, mid);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
						<td >&#8377;<%=rs.getString(3) %></td>
						<td >&#8377;<%=rs.getString(4) %></td>
						<td >&#8377;<%=rs.getString(5) %></td>
					</tr>
				<% } 
					rs.close();
					ps.close();
					cn.close();
					}
				catch(SQLException se){
					se.printStackTrace();
					obj.releaseConnectionSourcePS(rs, ps, cn);
					}%>
			</table>
				<div class="actionBtn">
					<div>
						<form action="generatedBillRice.jsp" method="post">
							<input type="hidden" name="sdate" value="<%=sdate%>">
							<input type="hidden" name="mid" value="<%=mid%>">
							<input class="print_btn" type="submit" value="PRINT">
						</form>
					</div>
					<a href="MahajanDelInputForm.jsp"><input class="mahajan_button" type="button" value="DELETE"></a>	
				</div>
			<a href="WelcomeMahajan.jsp" class="gobackHome">Go Home</a>
		</div>
	</div>
</body>
</html>