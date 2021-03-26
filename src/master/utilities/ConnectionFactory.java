package master.utilities;

import java.sql.*;

public class ConnectionFactory {

	Connection cn = null;

	public Connection getCon(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sayan?autoReconnect=true&useSSL=false","jee","jee");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return cn;
	}

	public void releaseConnectionSourceST(ResultSet rs, Statement st, Connection cn) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

		} catch (Exception e) {
			System.out.println("Esception is closing Resultset" + e.getMessage());
		}
		try {
			if (st != null) {
				st.close();
				st = null;
			}

		} catch (Exception e) {
			System.out.println("Exception is closing Statement " + e.getMessage());
		}
		try {
			if (cn != null) {
				cn.close();
				cn = null;
			}
		} catch (Exception e) {
			System.out.println("Exception is closing Connection " + e.getMessage());
		}

	}

	public void releaseConnectionSourcePS(ResultSet rs, PreparedStatement ps, Connection cn) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

		} catch (Exception e) {
			System.out.println("Esception is closing Resultset" + e.getMessage());
		}
		try {
			if (ps != null) {
				ps.close();
				ps = null;
			}

		} catch (Exception e) {
			System.out.println("Exception is closing PreparedStatementStatement " + e.getMessage());
		}
		try {
			if (cn != null) {
				cn.close();
				cn = null;
			}
		} catch (Exception e) {
			System.out.println("Exception is closing Connection " + e.getMessage());
		}

	}

}
