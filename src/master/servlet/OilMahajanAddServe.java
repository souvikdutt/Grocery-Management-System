package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilMahajanDAO;
import master.DTO.OilMahajanMainDTO;

/**
 * Servlet implementation class OilMahajanAddServe
 */
public class OilMahajanAddServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String mid=request.getParameter("mid");
		String mname=request.getParameter("mname");
		String total_bill=request.getParameter("total_bill");
		String total_pay=request.getParameter("total_pay");
		String total_due=request.getParameter("total_due");
		
		OilMahajanMainDTO mmdto=new OilMahajanMainDTO();
		mmdto.setMid(mid);
		mmdto.setMname(mname);
		mmdto.setTotal_bill(total_bill);
		mmdto.setTotal_pay(total_pay);
		mmdto.setTotal_due(total_due);
		
		OilMahajanDAO mmdao=new OilMahajanDAO();
		mmdao.InsertMahajanMain(mmdto);
		request.setAttribute("success", "Mahajan ID saved successfully!!");
		RequestDispatcher rd = request.getRequestDispatcher("OilMahajanAdd.jsp");
		rd.forward(request, response);
	}

}
