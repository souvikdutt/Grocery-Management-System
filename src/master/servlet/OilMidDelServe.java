package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilMahajanDAO;

/**
 * Servlet implementation class OilMidDelServe
 */
public class OilMidDelServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String mid=request.getParameter("mid");
		
		OilMahajanDAO mdao=new OilMahajanDAO();
		mdao.DelMahajanAllDetails(mid);
		request.setAttribute("done", "Mahajan ID deleted!!");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeMahajanOil.jsp");
		rd.forward(request, response);
	}

}
