package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CashDAO;
import master.DTO.CashBillDTO;

/**
 * Servlet implementation class CashBillAddServe
 */
public class CashBillAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String total_bill=request.getParameter("total_bill");
		
		CashBillDTO cbdto=new CashBillDTO();
		cbdto.setCash_date(cash_date);
		cbdto.setBillno(billno);
		cbdto.setTotal_bill(total_bill);
		
		CashDAO cdao=new CashDAO();
		cdao.InsertCashBill(cbdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeCash.jsp");
		rd.forward(request, response);
	}

}
