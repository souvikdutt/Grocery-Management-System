package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCashDAO;

/**
 * Servlet implementation class OilCashDelBidDateCheck
 */
public class OilCashDelBidDateCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		
		int flag=0;
		OilCashDAO cdao=new OilCashDAO();
		flag=cdao.CheckDuplicateBillNODate(cash_date, billno);
		if(flag==0) {
			request.setAttribute("error", "Please check your Date and Bill!!!");
			RequestDispatcher rd=request.getRequestDispatcher("OilCashBillDelInputForm.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("OilCashDeleteServe");
			rd.forward(request, response);
		}
	}

}
