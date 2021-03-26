package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.StockRiceDTO;
import master.utilities.ConnectionFactory;

public class StockRiceDAO {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	String insert_stockRice = "insert into stock_rice values(?,?,?)";

	String select_stockRice = "select rice_id,bag from stock_rice";
	String select_riceId="select distinct rice_id from stock_rice";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertStockRice(StockRiceDTO srdto) {

		try {
			ps = cn.prepareStatement(insert_stockRice);
			ps.setString(1, srdto.getRice_id());
			ps.setString(2, srdto.getBag());
			ps.setString(3, srdto.getNofbag());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public int CheckRiceId(String rice_id, String bag) {
		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_stockRice);
			while (rs.next()) {
				if (rs.getString(1).equals(rice_id) && rs.getString(2).equals(bag)) {
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

	public ArrayList getRiceId() {
		
		ArrayList arr=new ArrayList();

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_riceId);
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
