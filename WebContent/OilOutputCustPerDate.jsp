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
					String cdate=request.getParameter("cdate");
					String cid=request.getParameter("cid");
	
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_custRice="select * from cust_oil where cdate=? and cid=? ";
					String select_custBill="select * from oilcust_bill where cdate=? and cid=?";
					String select_Cname="select cname from oilcust_main where cid=?";
							
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
						ps=cn.prepareStatement(select_Cname);
						ps.setString(1, cid);
						rs=ps.executeQuery();
						if(rs.next()){%>
							<div class="billHeader">
								<div>
								<lable>Customer ID : </lable>
								<b><%=cid %></b>
								</div>
								
								<div>
								<lable>Name : </lable>
								<b><%=rs.getString(1) %></b>
								</div>
								
								<div>
								<lable>Selling date : </lable>
								<b><%=cdate %></b>
								</div>
							</div>
						<%}
					}catch(SQLException se){
						se.printStackTrace();
					}
					
					try{
					ps=cn.prepareStatement(select_custRice);
					ps.setString(1, cdate);
					ps.setString(2, cid);
					rs=ps.executeQuery(); %>
					<table>
					<tr>
						<td class="col">OIL ID</td>
						<td class="col">CATEGORY</td>
						<td class="col">QUANTITY</td>
						<td class="col">PRICE</td>
					</tr> <%
					while(rs.next()){
				%>
					<tr>
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
			<table class="totalTable">
				<tr>
					<td class="col">TOTAL BILL(TO DATE)</td>
					<td class="col">PAYMENT(TO DATE)</td>
					<td class="col">DUES(TO DATE)</td>
				</tr>
				<%	
					try{
					ps=cn.prepareStatement(select_custBill);
					ps.setString(1, cdate);
					ps.setString(2, cid);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
					<td >&#8377;<%=rs.getString(3) %></td>
					<td >&#8377;<%=rs.getString(4) %></td>
					<td >&#8377;<%=rs.getString(5) %></td>
					</tr>
				<% } 
					}
				catch(SQLException se){
					se.printStackTrace();
					}
					obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			</table>
			<div class="actionBtn">
					<div>
						<form action="CustGeneratedBill.jsp" method="post">
							<input type="hidden" name="cdate" value="<%=cdate%>">
							<input type="hidden" name="cid" value="<%=cid%>">
							<input class="print_btn" type="submit" value="PRINT">
						</form>
					</div>
					<div>
						<a href="OilCustDelInputForm.jsp"><input class="mahajan_button" type="button" value="DELETE"></a>
					</div>
			</div>
			<a href="OilWelcomeDueCust.jsp" class="gobackHome">click Home</a>
		</div>
	</div>
</body>
</html>