package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCashDAO;
import master.DTO.OilCashBillDTO;

/**
 * Servlet implementation class OilCashDeleteServe
 */
public class OilCashDeleteServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		
		OilCashBillDTO cbdto=new OilCashBillDTO();
		cbdto.setCash_date(cash_date);
		cbdto.setBillno(billno);
		
		OilCashDAO cdao=new OilCashDAO();
		cdao.delCashRiceBill(cbdto);
		request.setAttribute("done", "Successfully deleted!!");
		RequestDispatcher rd=request.getRequestDispatcher("OilWelcomeCash.jsp");
		rd.forward(request, response);
	}

}
