package master.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import master.utilities.ConnectionFactory;

/**
 * Servlet implementation class LogInCheck
 */
public class LogInCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String gmail = request.getParameter("gmail");
		String pass = request.getParameter("pass");
		int flag = 0;

		ConnectionFactory obj = new ConnectionFactory();
		Connection cn = (Connection) obj.getCon();
		Statement st = null;
		ResultSet rs = null;
		String select_sql = "select gmail,password from admin_signup";
		try {
			st = (Statement) cn.createStatement();
			rs = st.executeQuery(select_sql);
			while (rs.next()) {
				if ((rs.getString(1).equals(gmail)) && (rs.getString(2).equals(pass))) {
					flag = 1;
					HttpSession session = request.getSession();
					session.setAttribute("username", gmail);
					response.sendRedirect("Home.jsp");
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		if (flag == 0) {
			request.setAttribute("error", "You've entered wrong username/password");
			RequestDispatcher rd = request.getRequestDispatcher("LogIn.jsp");
			rd.forward(request, response);
		}
		obj.releaseConnectionSourceST(rs, st, cn);
	}

}
