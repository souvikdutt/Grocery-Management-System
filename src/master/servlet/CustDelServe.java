package master.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import master.DAO.CustomerDAO;
import master.DTO.CustomerBillDTO;
import master.utilities.ConnectionFactory;

/**
 * Servlet implementation class CustDelServe
 */
public class CustDelServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		
		CustomerBillDTO cbdto=new CustomerBillDTO();
		cbdto.setCdate(cdate);
		cbdto.setCid(cid);
		
		CustomerDAO cdao=new CustomerDAO();
		
		
		ConnectionFactory obj=new ConnectionFactory();
		Connection cn=(Connection) obj.getCon();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String select_bill="select total_bill,total_due from cust_bill where cdate=? and cid=?";
		String x="0.00";
		
		try {
			ps=(PreparedStatement) cn.prepareStatement(select_bill);
			ps.setString(1, cdate);
			ps.setString(2, cid);
			rs=(ResultSet)ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(x) && rs.getString(2).equals(x)) {
					cdao.ExPaymentDelBill(cbdto);
				}
				else {
					cdao.DelCustRiceBill(cbdto);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		request.setAttribute("done", "Successfully deleted!!");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeDueCust.jsp");
		rd.forward(request, response);
	}

}
