package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.StockRiceDAO;

/**
 * Servlet implementation class RiceAddCheckRiceId
 */
public class RiceAddCheckRiceId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		StockRiceDAO sdao=new StockRiceDAO();
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		
		int flag=0;
		flag=sdao.CheckRiceId(rice_id, bag);
		
		if(flag==1)
		{

			request.setAttribute("error", "Rice ID & Bag already exists.");
			RequestDispatcher rd=request.getRequestDispatcher("RiceAdd.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("RiceAddServe");
			rd.forward(request, response);
		}
	}

}
