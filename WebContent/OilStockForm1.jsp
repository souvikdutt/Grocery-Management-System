<%@page import="master.DAO.OilMahajanDAO"%>
<%@ page import="java.util.*"%>
<%@page import="master.utilities.ConnectionFactory"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Form1</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainOil">
		<form class="box" action="OilStockForm1CheckMidDate" method="post">

			<span style="color: red; font-size: 15px">
		    <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>


			<input class="date" type="date" name="sdate" placeholder="Date"
				required="required">
			<%
				OilMahajanDAO mdao=new OilMahajanDAO();
				ArrayList arr = new ArrayList();

				arr = mdao.getMid();
				Iterator itr = arr.iterator();
				
			%>
			<select id="rice_id" name="mid" required="required">
				<%
					while (itr.hasNext()) {
						
						String x=(String) itr.next();
				%>
				<option value="<%=x%>"><%=x%></option>
				<%
					}
				%>
			</select> <input type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>