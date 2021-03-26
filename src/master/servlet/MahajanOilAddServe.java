package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilMahajanDAO;
import master.DTO.MahajanOilDTO;

/**
 * Servlet implementation class MahajanOilAddServe
 */
public class MahajanOilAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String sdate=request.getParameter("sdate");
		String mid=request.getParameter("mid");
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		String quan=request.getParameter("quan");
		String price=request.getParameter("price");
		
		MahajanOilDTO modto=new MahajanOilDTO();
		modto.setSdate(sdate);
		modto.setMid(mid);
		modto.setOil_id(oil_id);
		modto.setCategory(category);
		modto.setQuan(quan);
		modto.setPrice(price);
		
		OilMahajanDAO omdao=new OilMahajanDAO();
		omdao.InsertMahajanOil(modto);
		request.setAttribute("success", "saved!!");
		RequestDispatcher rd=request.getRequestDispatcher("OilStockForm2.jsp");
		rd.forward(request, response);
	}

}
