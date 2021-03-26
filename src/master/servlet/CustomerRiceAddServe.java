package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CustomerDAO;
import master.DTO.CustomerRiceDTO;

/**
 * Servlet implementation class CustomerRiceAddServe
 */
public class CustomerRiceAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		String nofbag=request.getParameter("nofbag");
		String total_weight=request.getParameter("total_weight");
		String rate_quin=request.getParameter("rate_quin");
		String price=request.getParameter("price");
		
		CustomerRiceDTO crdto=new CustomerRiceDTO();
		crdto.setCdate(cdate);
		crdto.setCid(cid);
		crdto.setRice_id(rice_id);
		crdto.setBag(bag);
		crdto.setNofbag(nofbag);
		crdto.setTotal_weight(total_weight);
		crdto.setRate_quin(rate_quin);
		crdto.setPrice(price);
		
		CustomerDAO cdao=new CustomerDAO();
		cdao.InsertCustRice(crdto);
		request.setAttribute("done", "Successfully Saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("CustOrderForm2.jsp");
		rd.forward(request, response);
	}

}
