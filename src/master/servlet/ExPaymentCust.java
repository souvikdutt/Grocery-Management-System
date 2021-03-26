package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CustomerDAO;
import master.DTO.CustomerBillDTO;

/**
 * Servlet implementation class ExPaymentCust
 */
public class ExPaymentCust extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String total_bill=request.getParameter("total_bill");
		String payment=request.getParameter("payment");
		String due=request.getParameter("due");
		
		CustomerBillDTO cbdto=new CustomerBillDTO();
		cbdto.setCdate(cdate);
		cbdto.setCid(cid);
		cbdto.setTotal_bill(total_bill);
		cbdto.setPayment(payment);
		cbdto.setDue(due);
		
		CustomerDAO cdao=new CustomerDAO();
		cdao.ExPaymentCust(cbdto);
		request.setAttribute("done", "Payment Successfully!!");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeDueCust.jsp");
		rd.forward(request, response);
	}

}
