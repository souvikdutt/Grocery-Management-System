<%@page import="master.DAO.OilCustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	OilCustomerDAO cdao=new OilCustomerDAO();
	
	String cid=request.getParameter("cid");
	int flag=0;
	flag=cdao.CheckCid(cid);
	
	if(flag==1)
	{
		request.setAttribute("error", "Customer ID is already exist!");
		RequestDispatcher rd = request.getRequestDispatcher("OilCustomerAdd.jsp");
		rd.forward(request, response);
	}
	else{
		RequestDispatcher rd=request.getRequestDispatcher("OilCustomerAddServe");
		rd.forward(request, response);
	}

%>

</body>
</html>