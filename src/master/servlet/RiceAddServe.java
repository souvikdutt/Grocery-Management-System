package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.StockRiceDAO;
import master.DTO.StockRiceDTO;

/**
 * Servlet implementation class RiceAddServe
 */
public class RiceAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		String nofbag=request.getParameter("nofbag");
		
		StockRiceDTO srdto=new StockRiceDTO();
		srdto.setRice_id(rice_id);
		srdto.setBag(bag);
		srdto.setNofbag(nofbag);
		
		StockRiceDAO srdao=new StockRiceDAO();
		srdao.InsertStockRice(srdto);
		request.setAttribute("saved", "Successfully saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("RiceAdd.jsp");
		rd.forward(request, response);
	}

}
