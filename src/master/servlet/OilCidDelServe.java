package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCustomerDAO;

/**
 * Servlet implementation class OilCidDelServe
 */
public class OilCidDelServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cid=request.getParameter("cid");
		
		OilCustomerDAO cdao=new OilCustomerDAO();
		cdao.DelDueCustAllDetails(cid);
		
		request.setAttribute("done", "Customer ID deleted Successfully!!");
		RequestDispatcher rd=request.getRequestDispatcher("OilWelcomeDueCust.jsp");
		rd.forward(request, response);
	}

}
