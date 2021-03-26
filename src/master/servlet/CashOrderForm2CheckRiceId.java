package master.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.CashDAO;
import master.DAO.StockRiceDAO;

/**
 * Servlet implementation class CashOrderForm2CheckRiceId
 */
public class CashOrderForm2CheckRiceId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String cash_date=request.getParameter("cash_date");
		String billno=request.getParameter("billno");
		String rice_id=request.getParameter("rice_id");
		String bag=request.getParameter("bag");
		StockRiceDAO sdao=new StockRiceDAO();
		CashDAO cdao=new CashDAO();
		
		int flag=0,flag1=1;
		flag=sdao.CheckRiceId(rice_id, bag);
		flag1=cdao.CheckCashRiceDuplicate(cash_date, billno, rice_id, bag);
		
		if(flag==1 && flag1==1)
		{
			RequestDispatcher rd=request.getRequestDispatcher("CashRiceAddServe");
			rd.forward(request, response);
		}
		else if(flag==0){
			request.setAttribute("error", "Bag doesn't exists");
			RequestDispatcher rd=request.getRequestDispatcher("CashOrderForm2.jsp");
			rd.forward(request, response);
		}
		else if(flag1==0){
			request.setAttribute("error", "Already added this bag please Check!!");
			RequestDispatcher rd=request.getRequestDispatcher("CashOrderForm2.jsp");
			rd.forward(request, response);
		}
	}

}
