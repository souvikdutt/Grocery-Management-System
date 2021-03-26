<%@page import="master.DAO.CustomerDAO"%>
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
		<form class="box" action="CidDelServe" method="post">
			<%
				CustomerDAO obj=new CustomerDAO();
				ArrayList arr=new ArrayList();
					arr=obj.getCid();
					Iterator itr=arr.iterator();
					%>
					<select id="rice_id" name="cid"><%
					while(itr.hasNext()){
						String x =	(String) itr.next();
					%>
				          <option value="<%=x %>"><%=x %></option>
					<%}%> </select>
				<input type="submit" value="DELETE">
		</form>
	</div>

</body>
</html>