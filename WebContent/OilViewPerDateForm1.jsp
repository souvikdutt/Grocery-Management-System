<%@page import="master.DAO.OilMahajanDAO"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Form1</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainOil">
		<form class="box" action="OilShowPerDate.jsp" >
			<input type="date" class="date" name="sdate" placeholder="Date" required="required">
			<%
				OilMahajanDAO obj=new OilMahajanDAO();
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