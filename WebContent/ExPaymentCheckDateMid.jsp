<%@page import="master.DAO.MahajanDAO"%>
<%@ page import="java.sql.*" %>
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
	MahajanDAO mdao=new MahajanDAO();
	String sdate=request.getParameter("sdate");
	String mid=request.getParameter("mid");
	
	int flag=0;
	flag=mdao.CheckDateMid(sdate, mid);
	
	if(flag==1)
	{
		RequestDispatcher rd=request.getRequestDispatcher("ExPaymentMahajan");
		rd.forward(request, response);
	}
	else{
		request.setAttribute("error", "Already Mahajan have a bill to date..Please check!!");
		RequestDispatcher rd=request.getRequestDispatcher("ExPaymentForm.jsp");
		rd.forward(request, response);
	}
%>
</body>
</html>