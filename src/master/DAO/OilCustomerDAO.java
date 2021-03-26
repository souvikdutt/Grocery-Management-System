package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.CustomerOilDTO;
import master.DTO.OilCustomerBillDTO;
import master.DTO.OilCustomerMainDTO;
import master.DTO.StockOilDTO;
import master.utilities.ConnectionFactory;

public class OilCustomerDAO {
	
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String Oilinsert_custMain = "insert into oilcust_main values(?,?,?,?,?,?)";
	String Oilinsert_custRice = "insert into cust_oil values(?,?,?,?,?,?)";
	String Oilinsert_custBill = "insert into oilcust_bill values(?,?,?,?,?)";

	String Oilupd_stockRice = "update stock_oil set quantity=? where oil_id=? and category=?";
	String Oilselect_stockRice = "select quantity from stock_oil where oil_id=? and category=?";

	String Oilupd_custMain = "update oilcust_main set total_bill=?,total_pay=?,total_due=? where cid=?";
	String Oilselect_cust = "select total_bill,total_pay,total_due from oilcust_main where cid=?";

	String Oilselect_CustCid = "select cid from oilcust_main";

	String Oilselect_CustBill = "select total_bill,total_pay,total_due from oilcust_bill where cdate=? and cid=?";
	String Oildelete_CustBill = "delete from oilcust_bill where cdate=? and cid=?";

	String Oilselect_CustRice = "select oil_id,category,quantity from cust_oil where cdate=? and cid=?";
	String Oildelete_CustRice = "delete from cust_oil where cdate=? and cid=?";

	String Oilselect_CidDate = "select cdate,cid from oilcust_bill";
	String OilduplicateCheck = "select cdate,cid,oil_id,category from cust_oil";

	String Oildelall_cMain = "delete from oilcust_main where cid=?";
	String Oildelall_cRice = "delete from cust_oil where cid=?";
	String Oildelall_cBilling = "delete from oilcust_bill where cid=?";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertCustMain(OilCustomerMainDTO cmdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_custMain);
			ps.setString(1, cmdto.getCid());
			ps.setString(2, cmdto.getCname());
			ps.setString(3, cmdto.getPhno());
			ps.setString(4, cmdto.getTotal_bill());
			ps.setString(5, cmdto.getTotal_pay());
			ps.setString(6, cmdto.getTotal_due());

			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}
	public void InsertCustBill(OilCustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_custBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.setString(3, cbdto.getTotal_bill());
			ps.setString(4, cbdto.getPayment());
			ps.setString(5, cbdto.getDue());
			ps.executeUpdate();

			String bill = cbdto.getTotal_bill();
			String pay = cbdto.getPayment();
			String due = cbdto.getDue();
			double billc, payc, duec;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);
			duec = Double.parseDouble(due);

			ps = cn.prepareStatement(Oilselect_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			if (rs.next()) {
				String PrevBill = rs.getString(1);
				String PrevPay = rs.getString(2);
				String PrevDue = rs.getString(3);

				double PrevBillc, PrevPayc, PrevDuec;
				PrevBillc = Double.parseDouble(PrevBill);
				PrevPayc = Double.parseDouble(PrevPay);
				PrevDuec = Double.parseDouble(PrevDue);

				double fbill, fpay, fdue;
				fbill = PrevBillc + billc;
				fpay = PrevPayc + payc;
				fdue = PrevDuec + duec;

				String fnBill, fnPay, fnDue;
				fnBill = String.valueOf(fbill);
				fnPay = String.valueOf(fpay);
				fnDue = String.valueOf(fdue);

				ps = cn.prepareStatement(Oilupd_custMain);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, cbdto.getCid());
			}
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}
	public void InsertCustOil(CustomerOilDTO cdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_custRice);
			ps.setString(1, cdto.getCdate());
			ps.setString(2, cdto.getCid());
			ps.setString(3, cdto.getOil_id());
			ps.setString(4, cdto.getCategory());
			ps.setString(5, cdto.getQuan());
			ps.setString(6, cdto.getPrice());
			ps.executeUpdate();

			int nquan = Integer.parseInt(cdto.getQuan());

			ps = cn.prepareStatement(Oilselect_stockRice);
			ps.setString(1, cdto.getOil_id());
			ps.setString(2, cdto.getCategory());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevquan = Integer.parseInt(rs.getString(1));
				int number = prevquan - nquan;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(Oilupd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, cdto.getOil_id());
				ps.setString(3, cdto.getCategory());
				ps.executeUpdate();
			}
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}
	public void ExPaymentCust(OilCustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(Oilinsert_custBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.setString(3, cbdto.getTotal_bill());
			ps.setString(4, cbdto.getPayment());
			ps.setString(5, cbdto.getDue());
			ps.executeUpdate();

			String bill = cbdto.getTotal_bill();
			String pay = cbdto.getPayment();
			double billc, payc;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);

			ps = cn.prepareStatement(Oilselect_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			if (rs.next()) {
				String PrevBill = rs.getString(1);
				String PrevPay = rs.getString(2);
				String PrevDue = rs.getString(3);

				double PrevBillc, PrevPayc, PrevDuec;
				PrevBillc = Double.parseDouble(PrevBill);
				PrevPayc = Double.parseDouble(PrevPay);
				PrevDuec = Double.parseDouble(PrevDue);

				double fbill, fpay, fdue;
				fbill = PrevBillc + billc;
				fpay = PrevPayc + payc;
				fdue = PrevDuec - payc;

				String fnBill, fnPay, fnDue;
				fnBill = String.valueOf(fbill);
				fnPay = String.valueOf(fpay);
				fnDue = String.valueOf(fdue);

				ps = cn.prepareStatement(Oilupd_custMain);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, cbdto.getCid());
			}
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);

	}

	public void DelCustRiceBill(OilCustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(Oilselect_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(Oilselect_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill = 0, fpay = 0, fdue = 0;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue - ddue;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(Oilupd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(Oildelete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(Oilselect_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
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

			ps = cn.prepareStatement(Oildelete_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public void ExPaymentDelBill(OilCustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(Oilselect_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(Oilselect_cust);
			ps.setString(1, cbdto.getCid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill = 0, fpay = 0, fdue = 0;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue + dpay;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(Oilupd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(Oildelete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public int CheckDateCid(String cdate, String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(Oilselect_CidDate);
			while (rs.next()) {

				if (rs.getString(1).equals(cdate) && rs.getString(2).equals(cid)) {
					return 0;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;

	}
	
	public ArrayList getCid() {
		ArrayList arr=new ArrayList();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(Oilselect_CustCid);
			while(rs.next()){
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}
	public int CheckCustOilDuplicate(String cdate, String cid, String oil_id, String category) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(OilduplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cdate) && rs.getString(2).equals(cid) && rs.getString(3).equals(oil_id)
						&& rs.getString(4).equals(category)) {
					return 0;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 1;
	}
	
	public int CheckCid(String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(Oilselect_CustCid);
			while (rs.next()) {
				if (rs.getString(1).equals(cid))
					return 1;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return 0;
	}
	public void DelDueCustAllDetails(String cid) {

		try {
			ps = cn.prepareStatement(Oildelall_cBilling);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(Oildelall_cRice);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(Oildelall_cMain);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
}
