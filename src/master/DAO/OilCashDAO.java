package master.DAO;

import java.util.*;

import master.DTO.CashOilDTO;
import master.DTO.OilCashBillDTO;
import master.DTO.StockOilDTO;
import master.utilities.ConnectionFactory;

import java.sql.*;

public class OilCashDAO {

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	String Oilinsert_cashRice = "insert into cash_oil values(?,?,?,?,?,?)";
	String Oilinsert_cashBill = "insert into oilcash_bill values(?,?,?)";

	String Oilupd_stockRice = "update stock_oil set quantity=? where oil_id=? and category=?";
	String Oilselect_stockRice = "select quantity from stock_oil where oil_id=? and category=?";

	String Oildelete_cashRice = "delete from cash_oil where cash_date=? and billno=?";
	String Oildelete_cashBill = "delete from oilcash_Bill where cash_date=? and billno=?";

	String Oilselect_CashRice = "select oil_id,category,quantity from cash_oil where cash_date=? and billno=?";

	String Oilselect_CashBill = "select Cash_date,billno from oilcash_bill";
	String OilduplicateCheck = "select cash_date,billno,oil_id,category from cash_oil";
	
	public void InsertCashOil(CashOilDTO crdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_cashRice);
			ps.setString(1, crdto.getCash_date());
			ps.setString(2, crdto.getBillno());
			ps.setString(3, crdto.getOil_id());
			ps.setString(4, crdto.getCategory());
			ps.setString(5, crdto.getQuantity());
			ps.setString(6, crdto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(crdto.getQuantity());

			ps = cn.prepareStatement(Oilselect_stockRice);
			ps.setString(1, crdto.getOil_id());
			ps.setString(2, crdto.getCategory());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevNofbag = Integer.parseInt(rs.getString(1));
				int number = prevNofbag - nnofbag;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(Oilupd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, crdto.getOil_id());
				ps.setString(3, crdto.getCategory());
				ps.executeUpdate();
			}
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertCashBill(OilCashBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_cashBill);
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

	public void delCashRiceBill(OilCashBillDTO cbdto) {
		
		ArrayList arr=new ArrayList();
		
		try {
			ps=cn.prepareStatement(Oildelete_cashBill);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();
			

			ps = cn.prepareStatement(Oilselect_CashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			rs = ps.executeQuery();
			while (rs.next()) {
				StockOilDTO sdto=new StockOilDTO();
				sdto.setOil_id(rs.getString(1));
				sdto.setCategory(rs.getString(2));
				sdto.setQuan(rs.getString(3));
				arr.add(sdto);
			}
			Iterator itr = arr.iterator();
			while (itr.hasNext()) {
				StockOilDTO sdto = (StockOilDTO) itr.next();
				ps = cn.prepareStatement(Oilselect_stockRice);
				ps.setString(1, sdto.getOil_id());
				ps.setString(2, sdto.getCategory());
				rs = ps.executeQuery();

				int nofbagc = Integer.parseInt(sdto.getQuan());
				if (rs.next()) {

					int prevNofbag = Integer.parseInt(rs.getString(1));
					int number = prevNofbag + nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(Oilupd_stockRice);
					ps.setString(1, snum);
					ps.setString(2, sdto.getOil_id());
					ps.setString(3, sdto.getCategory());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(Oildelete_cashRice);
			ps.setString(1, cbdto.getCash_date());
			ps.setString(2, cbdto.getBillno());
			ps.executeUpdate();
//			cn.commit();

			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public int CheckDuplicateBillNODate(String cash_date, String billno) {

		int flag = 0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(Oilselect_CashBill);
			while (rs.next()) {
				if (rs.getString(1).equals(cash_date) && rs.getString(2).equals(billno)) {
					flag = 1;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;
	}

	public int CheckCashOilDuplicate(String cash_date, String billno, String oil_id, String category) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(OilduplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cash_date) && rs.getString(2).equals(billno)
						&& rs.getString(3).equals(oil_id) && rs.getString(4).equals(category)) {
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
