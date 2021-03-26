package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.MahajanDAO;

/**
 * Servlet implementation class StockForm1CheckMidDate
 */
public class StockForm1CheckMidDate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		MahajanDAO mdao=new MahajanDAO();
		
		int flag=0;
		flag=mdao.CheckDateMid(sdate, mid);
		
		if(flag==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("StockForm2.jsp");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Already used same Mahajan Id and Date..Please check!!");
			RequestDispatcher rd=request.getRequestDispatcher("StockForm1.jsp");
			rd.forward(request, response);
		}
	}

}
