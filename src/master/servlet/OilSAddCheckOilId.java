package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.StockOilDAO;

/**
 * Servlet implementation class OilSAddCheckOilId
 */
public class OilSAddCheckOilId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		StockOilDAO sdao=new StockOilDAO();
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		
		int flag=0;
		flag=sdao.CheckOilId(oil_id, category);
		
		if(flag==1)
		{

			request.setAttribute("error", "Oil ID & Category already exists.");
			RequestDispatcher rd=request.getRequestDispatcher("OilAdd.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("OilAddServe");
			rd.forward(request, response);
		}
	}

}
