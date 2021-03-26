package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCashDAO;
import master.DTO.CashOilDTO;

/**
 * Servlet implementation class CashOilAddServe
 */
public class CashOilAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		String quantity=request.getParameter("quan");
		String price=request.getParameter("price");
		
		CashOilDTO cdto=new CashOilDTO();
		cdto.setCash_date(cash_date);
		cdto.setBillno(billno);
		cdto.setOil_id(oil_id);
		cdto.setCategory(category);
		cdto.setQuantity(quantity);
		cdto.setPrice(price);
		
		OilCashDAO cdao=new OilCashDAO();
		cdao.InsertCashOil(cdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("OilCashOrderForm2.jsp");
		rd.forward(request, response);
	}

}
