package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilMahajanDAO;
import master.DAO.StockOilDAO;

/**
 * Servlet implementation class OilStockForm2CheckOilId
 */
public class OilStockForm2CheckOilId extends HttpServlet {
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
		
		StockOilDAO sdao=new StockOilDAO();
		OilMahajanDAO mdao=new OilMahajanDAO();
		
		int flag=0,flag1=1;
		flag=sdao.CheckOilId(oil_id, category);
		flag1=mdao.CheckmahajanOilDuplicate(sdate, mid, oil_id, category);
		
		if(flag==1 && flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("MahajanOilAddServe");
			rd.forward(request, response);
		}
		else if(flag==0){
			request.setAttribute("error", "oil doesn't exists");
			RequestDispatcher rd=request.getRequestDispatcher("OilStockForm2.jsp");
			rd.forward(request, response);
		}
		else if(flag1==0){
			request.setAttribute("error", "Already added this oil please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("OilStockForm2.jsp");
			rd.forward(request, response);
		}
	}

}
