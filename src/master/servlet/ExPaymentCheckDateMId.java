package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.MahajanDAO;

/**
 * Servlet implementation class ExPaymentCheckDateMId
 */
public class ExPaymentCheckDateMId extends HttpServlet {
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
			RequestDispatcher rd=request.getRequestDispatcher("ExPaymentMahajan");
			rd.forward(request, response);
		}
		else{
			request.setAttribute("error", "Payment has already done at this date..Please check!!");
			RequestDispatcher rd=request.getRequestDispatcher("ExPaymentForm.jsp");
			rd.forward(request, response);
		}
	}

}
