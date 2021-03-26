<%@page import="master.DAO.MahajanDAO"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Form1</title>
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
		<form class="box" action="ShowPerDate.jsp" >
			<input type="date" class="date" name="sdate" placeholder="Date" required="required">
			<%
				MahajanDAO obj = new MahajanDAO();
				ArrayList arr=new ArrayList();
				
					arr=obj.getMid();
					Iterator itr=arr.iterator();
					%>
					<select id="rice_id" name="mid" required="required"><%
					while(itr.hasNext()){
						String x=(String) itr.next();
					%>
				          <option value="<%=x %>"><%=x %></option>
					<%}%> </select>
			 <input	type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>