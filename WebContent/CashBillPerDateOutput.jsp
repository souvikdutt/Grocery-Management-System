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
		
			<table>
				<tr>
					<td class="col">DATE</td>
					<td class="col">BILL NO</td>
					<td class="col">RICE ID</td>
					<td class="col">BAG(1/5/10/25/50/60)KG</td>
					<td class="col">NO OF BAGS</td>
					<td class="col">TOTAL WEIGHT</td>
					<td class="col">RATE/QUINTAL</td>
					<td class="col">PRICE</td>
				</tr>
				<%	
					String cash_date=request.getParameter("cash_date");
					String billno=request.getParameter("billno");
	
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_CashRice="select * from cash_rice where cash_date=? and billno=? ";
					String select_CashBill="select * from cash_bill where cash_date=? and billno=?";
							
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
					<td ><%=rs.getString(4) %>KG</td>
					<td ><%=rs.getString(5) %></td>
					<td ><%=rs.getString(6) %> Quin</td>
					<td >&#8377;<%=rs.getString(7) %></td>
					<td >&#8377;<%=rs.getString(8) %></td>
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
			<a href="CashBillDelInputForm.jsp"><input class="mahajan_button" type="button" value="DELETE"></a>
			
			<a href="WelcomeCash.jsp">click Home</a>
			
		</div>
	</div>
</body>
</html>