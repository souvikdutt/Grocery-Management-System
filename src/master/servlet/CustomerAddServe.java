package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CustomerDAO;
import master.DTO.CustomerMainDTO;

/**
 * Servlet implementation class CustomerAddServe
 */
public class CustomerAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");
		String phno=request.getParameter("phno");
		String total_bill=request.getParameter("total_bill");
		String total_pay=request.getParameter("total_pay");
		String total_due=request.getParameter("total_due");
		
		CustomerMainDTO cmdto=new CustomerMainDTO();
		cmdto.setCid(cid);
		cmdto.setCname(cname);
		cmdto.setPhno(phno);
		cmdto.setTotal_bill(total_bill);
		cmdto.setTotal_pay(total_pay);
		cmdto.setTotal_due(total_due);
		
		CustomerDAO cdao=new CustomerDAO();
		cdao.InsertCustMain(cmdto);
		request.setAttribute("done", "Successfully Added!");
		RequestDispatcher rd = request.getRequestDispatcher("CustomerAdd.jsp");
		rd.forward(request, response);
	}

}
