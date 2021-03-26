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

import master.DAO.OilMahajanDAO;
import master.DTO.MahajanOilBillDTO;
import master.utilities.ConnectionFactory;

/**
 * Servlet implementation class OilMahajanDelServe
 */
public class OilMahajanDelServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		
		MahajanOilBillDTO mdto=new MahajanOilBillDTO();
		mdto.setSdate(sdate);
		mdto.setMid(mid);
		
		OilMahajanDAO mdao=new OilMahajanDAO();
		
		ConnectionFactory obj=new ConnectionFactory();
		Connection cn=(Connection) obj.getCon();
		ResultSet rs=null;
		PreparedStatement ps=null;
		String select_bill="select total_bill,due from oilmahajan_billing where sdate=? and mid=?";
		String x="0";
		try {
			ps=(PreparedStatement) cn.prepareStatement(select_bill);
			ps.setString(1, sdate);
			ps.setString(2, mid);
			rs=(ResultSet)ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(x) && rs.getString(2).equals(x)) {
					mdao.OilExPaymentDel(mdto);
				}
				else {
					mdao.DelMahajanOilBill(mdto);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		request.setAttribute("success", "Deleted Successfully");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeMahajanOil.jsp");
		rd.forward(request, response);
	}

}
