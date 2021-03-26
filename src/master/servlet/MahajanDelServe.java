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

import master.DAO.MahajanDAO;
import master.DTO.MahajanBillDTO;
import master.utilities.ConnectionFactory;

/**
 * Servlet implementation class MahajanDelServe
 */
public class MahajanDelServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		
		MahajanBillDTO mbdto=new MahajanBillDTO();
		mbdto.setSdate(sdate);
		mbdto.setMid(mid);
		
		MahajanDAO mdao=new MahajanDAO();
		
		ConnectionFactory obj=new ConnectionFactory();
		Connection cn=(Connection) obj.getCon();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String select_bill="select total_bill,due from mahajan_billing where sdate=? and mid=?";
		String x="0.00";
		
		try {
			ps=(PreparedStatement) cn.prepareStatement(select_bill);
			ps.setString(1, sdate);
			ps.setString(2, mid);
			rs=(ResultSet)ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				if(rs.getString(1).equals(x) && rs.getString(2).equals(x)) {
					mdao.ExPaymentDel(mbdto);
				}
				else {
					mdao.DelMahajanRiceBill(mbdto);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		request.setAttribute("success", "Deleted Successfully");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeMahajan.jsp");
		rd.forward(request, response);
	}

}
