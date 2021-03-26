package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.OilCashDAO;
import master.DAO.StockOilDAO;

/**
 * Servlet implementation class CashOrderForm2CheckOilId
 */
public class CashOrderForm2CheckOilId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String oil_id=request.getParameter("oil_id");
		String category=request.getParameter("category");
		
		int flag=0,flag1=1;
		StockOilDAO sdao=new StockOilDAO();
		OilCashDAO cdao=new OilCashDAO();
		
		flag=sdao.CheckOilId(oil_id, category);
		flag1=cdao.CheckCashOilDuplicate(cash_date, billno, oil_id, category);
		
		if(flag==1 && flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("CashOilAddServe");
			rd.forward(request, response);
		}
		else if(flag==0){
			request.setAttribute("error", "Category doesn't exists");
			RequestDispatcher rd=request.getRequestDispatcher("OilCashOrderForm2.jsp");
			rd.forward(request, response);
		}
		else if(flag1==0){
			request.setAttribute("error", "Already added this oil please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("OilCashOrderForm2.jsp");
			rd.forward(request, response);
		}
	}

}
