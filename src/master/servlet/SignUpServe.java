package master.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import master.DAO.SignUpDAO;
import master.DTO.SignUpDTO;

/**
 * Servlet implementation class SignUpServe
 */
public class SignUpServe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String phno=request.getParameter("phno");
		String gmail=request.getParameter("gmail");
		String pass=request.getParameter("pass");
		
		SignUpDTO sdto=new SignUpDTO();
		sdto.setName(name);
		sdto.setPhno(phno);
		sdto.setGmail(gmail);
		sdto.setPass(pass);
		
		SignUpDAO sdao=new SignUpDAO();
		sdao.Insert_adminSignUp(sdto);
		response.sendRedirect("LogIn.jsp");
	}

}
