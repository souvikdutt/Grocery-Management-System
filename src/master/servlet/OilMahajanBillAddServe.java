package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilMahajanDAO;
import master.DTO.MahajanOilBillDTO;

/**
 * Servlet implementation class OilMahajanBillAddServe
 */
public class OilMahajanBillAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		String total_bill=request.getParameter("total_bill");
		String payment=request.getParameter("payment");
		String due=request.getParameter("due");
		
		MahajanOilBillDTO mbdto=new MahajanOilBillDTO();
		mbdto.setSdate(sdate);
		mbdto.setMid(mid);
		mbdto.setTotal_bill(total_bill);
		mbdto.setPayment(payment);
		mbdto.setDue(due);
		
		OilMahajanDAO omdao=new OilMahajanDAO();
		omdao.InsertOilMahajanBill(mbdto);
		request.setAttribute("done", "saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("WelcomeMahajanOil.jsp");
		rd.forward(request, response);
	}

}
