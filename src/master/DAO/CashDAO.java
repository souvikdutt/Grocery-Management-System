package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.CashBillDTO;
import master.DTO.CashRiceDTO;
import master.DTO.StockRiceDTO;
import master.utilities.ConnectionFactory;

public class CashDAO {
	
	ConnectionFactory obj=new ConnectionFactory();
	Connection cn=obj.getCon();
	
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String insert_cashRice = "insert into cash_rice values(?,?,?,?,?,?,?,?)";
	String insert_cashBill = "insert into cash_bill values(?,?,?)";
	
	String upd_stockRice = "update stock_rice set nofbag=? where rice_id=? and bag=?";
	String select_stockRice = "select nofbag from stock_rice where rice_id=? and bag=?";
	
	String delete_cashRice="delete from cash_rice where cash_date=? and billno=?";
	String delete_cashBill="delete from cash_Bill where cash_date=? and billno=?";
	
	String select_CashRice="select rice_id,bag,nofbag from cash_rice where cash_date=? and billno=?";
	
	String select_CashBill="select Cash_date,billno from cash_bill";
	String duplicateCheck = "select cash_date,billno,rice_id,bag from cash_rice";
	
	
	public void InsertCashRice(CashRiceDTO crdto) {

		try {
			ps = cn.prepareStatement(insert_cashRice);
			ps.setString(1, crdto.getCash_date());
			ps.setString(2, crdto.getBillno());
			ps.setString(3, crdto.getRice_id());
			ps.setString(4, crdto.getBag());
			ps.setString(5, crdto.getNofbag());
			ps.setString(6, crdto.getTotal_weight());
			ps.setString(7, crdto.getRate_quin());
			ps.setString(8, crdto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(crdto.getNofbag());

			ps = cn.prepareStatement(select_stockRice);
			ps.setString(1, crdto.getRice_id());
			ps.setString(2, crdto.getBag());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevNofbag = Integer.parseInt(rs.getString(1));
				int number = prevNofbag - nnofbag;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(upd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, crdto.getRice_id());
				ps.setString(3, crdto.getBag());
				ps.executeUpdate();
			}
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertCashBill(CashBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_cashBill);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.setString(3, cbdto.getTotal_bill());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public void delCashRiceBill(CashBillDTO cbdto) {
		
		ArrayList arr=new ArrayList();
		
		try {
			ps=cn.prepareStatement(delete_cashBill);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();
			

			ps = cn.prepareStatement(select_CashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			rs = ps.executeQuery();
			while (rs.next()) {
				StockRiceDTO sdto = new StockRiceDTO();
				sdto.setRice_id(rs.getString(1));
				sdto.setBag(rs.getString(2));
				sdto.setNofbag(rs.getString(3));
				arr.add(sdto);
			}
			Iterator itr = arr.iterator();
			while (itr.hasNext()) {
				StockRiceDTO sdto = (StockRiceDTO) itr.next();
				ps = cn.prepareStatement(select_stockRice);
				ps.setString(1, sdto.getRice_id());
				ps.setString(2, sdto.getBag());
				rs = ps.executeQuery();

				int nofbagc = Integer.parseInt(sdto.getNofbag());
				if (rs.next()) {

					int prevNofbag = Integer.parseInt(rs.getString(1));
					int number = prevNofbag + nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(upd_stockRice);
					ps.setString(1, snum);
					ps.setString(2, sdto.getRice_id());
					ps.setString(3, sdto.getBag());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(delete_cashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();
//			cn.commit();

			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public int CheckDuplicateBillNODate(String cash_date,String billno) {
		
		int flag=0;
		try {
			st=cn.createStatement();
			rs=st.executeQuery(select_CashBill);
			while(rs.next()) {
				if(rs.getString(1).equals(cash_date)&&rs.getString(2).equals(billno)) {
					flag=1;
					break;
				}
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;
	}
	public int CheckCashRiceDuplicate(String cash_date, String billno, String rice_id, String bag) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cash_date) && rs.getString(2).equals(billno) && rs.getString(3).equals(rice_id)
						&& rs.getString(4).equals(bag)) {
					return 0;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;
	}
}
