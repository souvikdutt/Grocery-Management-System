<%@page import="master.DAO.OilCustomerDAO"%>
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
	<div class="main">
		<form class="box" action="OilCustBillCheckServe" method="post">
		<span style="color:red;font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
		<input class="date" type="date" name="cdate" placeholder="Date" required="required">
			<%
				OilCustomerDAO obj=new OilCustomerDAO();
				ArrayList arr=new ArrayList();
				arr=obj.getCid();
				Iterator itr=arr.iterator();
					%>
					<select id="rice_id" name="cid"><%
					while(itr.hasNext()){
						String x=(String) itr.next();
					%>
				          <option value="<%=x %>"><%=x %></option>
					<%}%> </select>
			 <input	type="submit" value="DELETE">
		</form>
	</div>
</body>
</html>