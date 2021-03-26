package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.CustomerBillDTO;
import master.DTO.CustomerMainDTO;
import master.DTO.CustomerRiceDTO;
import master.DTO.StockRiceDTO;
import master.utilities.ConnectionFactory;

public class CustomerDAO {

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String insert_custMain = "insert into cust_main values(?,?,?,?,?,?)";
	String insert_custRice = "insert into cust_rice values(?,?,?,?,?,?,?,?)";
	String insert_custBill = "insert into cust_bill values(?,?,?,?,?)";

	String upd_stockRice = "update stock_rice set nofbag=? where rice_id=? and bag=?";
	String select_stockRice = "select nofbag from stock_rice where rice_id=? and bag=?";

	String upd_custMain = "update cust_main set total_bill=?,total_pay=?,total_due=? where cid=?";
	String select_cust = "select total_bill,total_pay,total_due from cust_main where cid=?";

	String select_CustCid = "select cid from cust_main";

	String select_CustBill = "select total_bill,total_pay,total_due from cust_bill where cdate=? and cid=?";
	String delete_CustBill = "delete from cust_bill where cdate=? and cid=?";

	String select_CustRice = "select rice_id,bag,nofbag from cust_rice where cdate=? and cid=?";
	String delete_CustRice = "delete from cust_rice where cdate=? and cid=?";

	String select_CidDate = "select cdate,cid from cust_bill";
	String duplicateCheck = "select cdate,cid,rice_id,bag from cust_rice";

	String delall_cMain = "delete from cust_main where cid=?";
	String delall_cRice = "delete from cust_rice where cid=?";
	String delall_cBilling = "delete from cust_bill where cid=?";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertCustMain(CustomerMainDTO cmdto) {

		try {
			ps = cn.prepareStatement(insert_custMain);
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

	public void InsertCustRice(CustomerRiceDTO crdto) {

		try {
			ps = cn.prepareStatement(insert_custRice);
			ps.setString(1, crdto.getCdate());
			ps.setString(2, crdto.getCid());
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

	public void InsertCustBill(CustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_custBill);
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

			ps = cn.prepareStatement(select_cust);
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

				ps = cn.prepareStatement(upd_custMain);
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

	public void ExPaymentCust(CustomerBillDTO cbdto) {

		try {
			ps = cn.prepareStatement(insert_custBill);
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

			ps = cn.prepareStatement(select_cust);
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

				ps = cn.prepareStatement(upd_custMain);
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

	public void DelCustRiceBill(CustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(select_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_cust);
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

			ps = cn.prepareStatement(upd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(delete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
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

			ps = cn.prepareStatement(delete_CustRice);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public void ExPaymentDelBill(CustomerBillDTO cbdto) {

		ArrayList arr = new ArrayList();

		try {
			ps = cn.prepareStatement(select_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_cust);
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

			ps = cn.prepareStatement(upd_custMain);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, cbdto.getCid());
			ps.executeUpdate();

			ps = cn.prepareStatement(delete_CustBill);
			ps.setString(1, cbdto.getCdate());
			ps.setString(2, cbdto.getCid());
			ps.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}


	public int CheckCid(String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CustCid);
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

	public ArrayList getCid() {
		ArrayList arr=new ArrayList();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CustCid);
			while(rs.next()){
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

	public int CheckDateCid(String cdate, String cid) {

		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_CidDate);
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

	public int CheckCustRiceDuplicate(String cdate, String cid, String rice_id, String bag) {
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(cdate) && rs.getString(2).equals(cid) && rs.getString(3).equals(rice_id)
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

	public void DelDueCustAllDetails(String cid) {

		try {
			ps = cn.prepareStatement(delall_cBilling);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(delall_cRice);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(delall_cMain);
			ps.setString(1, cid);
			ps.executeUpdate();
//			cn.commit();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

}
