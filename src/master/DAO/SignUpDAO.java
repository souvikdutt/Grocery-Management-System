package master.DAO;

import java.sql.*;

import master.DTO.SignUpDTO;
import master.utilities.ConnectionFactory;


public class SignUpDAO {
	
	PreparedStatement ps=null;
	ResultSet rs=null;
	String insert_adminSignUp="insert into admin_signup values(?,?,?,?)";
	
	ConnectionFactory obj=new ConnectionFactory();
	Connection cn=obj.getCon();
	
	public void Insert_adminSignUp(SignUpDTO sdto) {
		
		try {
			ps=cn.prepareStatement(insert_adminSignUp);
			ps.setString(1, sdto.getName());
			ps.setString(2, sdto.getPhno());
			ps.setString(3, sdto.getGmail());
			ps.setString(4, sdto.getPass());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

}
