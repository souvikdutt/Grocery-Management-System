package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCustomerDAO;
import master.DTO.CustomerOilDTO;

/**
 * Servlet implementation class CustomerOilAddServe
 */
public class CustomerOilAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		String quan=request.getParameter("quan");
		String price=request.getParameter("price");
		
		CustomerOilDTO cdto=new CustomerOilDTO();
		cdto.setCdate(cdate);
		cdto.setCid(cid);
		cdto.setOil_id(oil_id);
		cdto.setCategory(category);
		cdto.setQuan(quan);
		cdto.setPrice(price);
		
		OilCustomerDAO cdao=new OilCustomerDAO();
		cdao.InsertCustOil(cdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("OilCustOrderForm2.jsp");
		rd.forward(request, response);
	}

}
