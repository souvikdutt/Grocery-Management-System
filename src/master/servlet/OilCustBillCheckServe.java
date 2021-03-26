package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCustomerDAO;

/**
 * Servlet implementation class OilCustBillCheckServe
 */
public class OilCustBillCheckServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		
		OilCustomerDAO cdao=new OilCustomerDAO();
		
		int flag=0;
		flag=cdao.CheckDateCid(cdate, cid);
		
		if(flag==1)
		{
			request.setAttribute("error", "Check date and customer Id!!");
			RequestDispatcher rd=request.getRequestDispatcher("OilCustDelInputForm.jsp");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd=request.getRequestDispatcher("OilCustDelServe");
			rd.forward(request, response);
		}
	}

}
