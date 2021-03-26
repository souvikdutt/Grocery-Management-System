package master.DAO;

import java.util.*;

import master.DTO.StockOilDTO;
import master.utilities.ConnectionFactory;

import java.sql.*;

public class StockOilDAO {
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	String select_stockOil = "select oil_id,category from stock_oil";
	String insert_stockOil="insert into stock_oil values(?,?,?)";
	String select_oilID="select oil_id from stock_oil";
	
	ConnectionFactory obj=new ConnectionFactory();
	Connection cn=obj.getCon();
	
	public void InsertOilID(StockOilDTO sdto) {
		
		try {
			ps=cn.prepareStatement(insert_stockOil);
			ps.setString(1, sdto.getOil_id());
			ps.setString(2, sdto.getCategory());
			ps.setString(3, sdto.getQuan());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	
	public int CheckOilId(String oil_id, String category) {
		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_stockOil);
			while (rs.next()) {
				if (rs.getString(1).equals(oil_id) && rs.getString(2).equals(category)) {
					flag = 1;
					break;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
		return flag;
	}
	public ArrayList getOilID() {
		
		ArrayList arr=new ArrayList();
		try {
			st=cn.createStatement();
			rs=st.executeQuery(select_oilID);
			while(rs.next()) {
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

}
