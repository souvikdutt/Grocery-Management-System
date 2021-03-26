package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCustomerDAO;
import master.DAO.StockOilDAO;

/**
 * Servlet implementation class CustOrderForm2CheckOilId
 */
public class CustOrderForm2CheckOilId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cdate=request.getParameter("cdate");
		String cid=request.getParameter("cid");
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		
		StockOilDAO sdao=new StockOilDAO();
		OilCustomerDAO cdao=new OilCustomerDAO();
		int flag=0,flag1=1;
		flag=sdao.CheckOilId(oil_id, category);
		flag1=cdao.CheckCustOilDuplicate(cdate, cid, oil_id, category);
		if(flag==1 && flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("CustomerOilAddServe");
			rd.forward(request, response);
		}
		else if(flag==0){
			request.setAttribute("error", "This Oil doesn't exists");
			RequestDispatcher rd=request.getRequestDispatcher("OilCustOrderForm2.jsp");
			rd.forward(request, response);
		}
		else if(flag1==0){
			request.setAttribute("error", "Already added this Oil please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("OilCustOrderForm2.jsp");
			rd.forward(request, response);
		}
	}

}
