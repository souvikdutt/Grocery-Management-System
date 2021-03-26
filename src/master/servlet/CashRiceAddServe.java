package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CashDAO;
import master.DTO.CashRiceDTO;

/**
 * Servlet implementation class CashRiceAddServe
 */
public class CashRiceAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		String nofbag=request.getParameter("nofbag");
		String total_weight=request.getParameter("total_weight");
		String rate_quin=request.getParameter("rate_quin");
		String price=request.getParameter("price");
		
		CashRiceDTO crdto=new CashRiceDTO();
		crdto.setCash_date(cash_date);
		crdto.setBillno(billno);
		crdto.setRice_id(rice_id);
		crdto.setBag(bag);
		crdto.setNofbag(nofbag);
		crdto.setTotal_weight(total_weight);
		crdto.setRate_quin(rate_quin);
		crdto.setPrice(price);
		
		CashDAO cdao=new CashDAO();
		cdao.InsertCashRice(crdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("CashOrderForm2.jsp");
		rd.forward(request, response);
	}

}
