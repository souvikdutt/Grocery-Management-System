package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.MahajanDAO;
import master.DTO.MahajanRiceDTO;

/**
 * Servlet implementation class MahajanRiceAddServe
 */
public class MahajanRiceAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		String nofbag=request.getParameter("nofbag");
		String price=request.getParameter("price");
		
		MahajanRiceDTO mrdto=new MahajanRiceDTO();
		mrdto.setSdate(sdate);
		mrdto.setMid(mid);
		mrdto.setRice_id(rice_id);
		mrdto.setBag(bag);
		mrdto.setNofbag(nofbag);
		mrdto.setPrice(price);
		
		MahajanDAO mdao=new MahajanDAO();
		mdao.InsertMahajanRice(mrdto);
		request.setAttribute("success", "saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("StockForm2.jsp");
		rd.forward(request, response);
	}

}
