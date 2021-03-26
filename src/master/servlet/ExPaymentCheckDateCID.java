package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CustomerDAO;

/**
 * Servlet implementation class ExPaymentCheckDateCID
 */
public class ExPaymentCheckDateCID extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		CustomerDAO cdao=new CustomerDAO();
		
		int flag=0;
		flag=cdao.CheckDateCid(cdate, cid);
		
		if(flag==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("ExPaymentCust");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Payment has already done at this date..Please check!!");
			RequestDispatcher rd=request.getRequestDispatcher("ExPaymentCust.jsp");
			rd.forward(request, response);
		}
	}

}
