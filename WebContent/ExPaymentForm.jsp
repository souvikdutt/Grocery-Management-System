<%@page import="master.DAO.MahajanDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Log.css">
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
		<form class="box" action="ExPaymentCheckDateMId" method="post">
		<span style="color:red;font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<table>
			<tr>
				<td><span>Date :</span></td>
				<td><input class="date" type="date" name="sdate" required="required"></td>
			</tr>
			<tr>
				<td><span>Mahajan ID :</span></td>
				<td>
					<%
				MahajanDAO obj = new MahajanDAO();
				ArrayList arr=new ArrayList();
				
					arr=obj.getMid();
					Iterator itr=arr.iterator();
					%>
					<select id="rice_id" name="mid" required="required"><%
					while(itr.hasNext()){
						String x=(String)itr.next();
					%>
				          <option value="<%=x %>"><%=x%></option>
					<%}%> </select>
				</td>
			</tr>
			<tr>
				<td><span>Total Amount :</span></td>
				<td><input type="text" name="total_bill" id="total_bill" value="0"></td>
			</tr>
			<tr>
				<td><span>Payment of :</span></td>
				<td><input type="text" name="payment" placeholder="Payment To Mahajan" id="payment" required="required"></td>
			</tr>
			<tr>
				<td><span>Remain Due :</span></td>
				<td><input type="text" name="due" placeholder="Due" value="0"></td>
			</tr>
		</table> 
			<input	type="submit" value="SUBMIT AND EXIT"> 
		</form>
		</div>
</body>
</html>