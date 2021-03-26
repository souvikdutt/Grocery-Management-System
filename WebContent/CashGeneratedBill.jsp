<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invoice</title>
<link rel="stylesheet" href="css/generatedBill.css">
</head>
<body>
<div class="main">
        <div class="heading">INVOICE</div>
        <div class="biller">
            <div class="biller_info">
                <label>MR. PINTU SAHA</label>
                <span>Rice, Dal, Oil Whole-seller</span>
                <span>GSTIN/UIN : 19AWSPS3298L1ZA</span>
            </div>
            <div class="biller_address">
                <label>Address</label>
                <span>Ranihati, Joynagar, Panchla</span>
                <span>West Bengal, Code: 19</span>
                <span>+91 9874569871</span>
            </div>
        </div>
        <%	
			String cash_date=request.getParameter("cash_date");
			String billno=request.getParameter("billno");

			PreparedStatement ps=null;
			ResultSet rs=null;
			String select_CashRice="select * from cash_oil where cash_date=? and billno=? ";
			String select_CashBill="select * from oilcash_bill where cash_date=? and billno=?";
					
			ConnectionFactory obj=new ConnectionFactory();
			Connection cn=obj.getCon();
			
			try{
			ps=cn.prepareStatement(select_CashBill);
			ps.setString(1, cash_date);
			ps.setString(2, billno);
			rs=ps.executeQuery();
			while(rs.next()){
			%>
	        <div class="buyer">
	            <div class="buyer_info">
	                <span>(Cash Customer)</span>
	                <span>Bill No : <%=billno %></span>
	                <span>Billing date : <%=cash_date %></span>
	            </div>
	        </div>
        <%}
		}catch(SQLException se){
			se.printStackTrace();
		}%>
        <div class="detaied_bill">
            <table>
                <tr>
                    <th>Sl No.</th>
                    <th>Description of Goods</th>
                    <th>Category(Peti/Tin)</th>
                    <th>Quantity</th>
                    <th>Amount</th>
                </tr>
                <%
                try{
					ps=cn.prepareStatement(select_CashRice);
					ps.setString(1, cash_date);
					ps.setString(2, billno);
					rs=ps.executeQuery();
					int count = 1;
					while(rs.next()){
				%>
                <tr>
                    <td><%=count++ %></td>
                    <td><%=rs.getString(3) %></td>
                    <td><%=rs.getString(4) %></td>
                    <td><%=rs.getString(5) %></td>
                    <td><%=rs.getString(6) %></td>
                </tr>
                <% }
					}
					catch(SQLException se){
						se.printStackTrace();
					}%>
            </table>
            </div>
            <div class="total_details">
            <%	
				try{
				ps=cn.prepareStatement(select_CashBill);
				ps.setString(1, cash_date);
				ps.setString(2, billno);
				rs=ps.executeQuery();
				while(rs.next()){
			%>
	            <table>
	                <tr>
	                    <th>Total Amount -&nbsp;</th>
	                    <td><%=rs.getString(3) %></td>
	                </tr>
	            </table>
            <% } 
				}
				catch(SQLException se){
					se.printStackTrace();
					obj.releaseConnectionSourcePS(rs, ps, cn);
			}%>
	        </div>
	        <hr>
    </div>
    <div class="footer">
        <span>This is a software generated invoice</span>
    </div>
</body>
</html>