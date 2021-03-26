package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.MahajanBillDTO;
import master.DTO.MahajanMainDTO;
import master.DTO.MahajanRiceDTO;
import master.DTO.StockRiceDTO;
import master.utilities.ConnectionFactory;

public class MahajanDAO {

	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String insert_mahajanMain = "insert into mahajan_main values(?,?,?,?,?)";
	String insert_mahajanRice = "insert into mahajan_rice values(?,?,?,?,?,?)";
	String insert_mahajanBill = "insert into mahajan_billing values(?,?,?,?,?)";
	String mahajanmain_upd = "update mahajan_main set total_bill=?,total_pay=?,total_due=? where mid=?";
	String select_mahajan = "select total_bill,total_pay,total_due from mahajan_main where mid=?";
	String upd_stockRice = "update stock_rice set nofbag=? where rice_id=? and bag=?";
	String select_stockRice = "select nofbag from stock_rice where rice_id=? and bag=?";

	String select_mahajanMain = "select mid from mahajan_main";

	String select_MahajanBill = "select total_bill,payment,due from mahajan_billing where sdate=? and mid=?";
	String del_MahajanBill = "delete from mahajan_billing where sdate=? and mid=?";

	String select_MahajanRice = "select rice_id,bag,nofbag from mahajan_rice where sdate=? and mid=?";
	String delete_MahajanRice = "delete from mahajan_rice where sdate=? and mid=?";

	String select_midDate = "select sdate,mid from mahajan_billing";
	String duplicateCheck = "select sdate,mid,rice_id,bag from mahajan_rice";

	String delall_mMain = "delete from mahajan_main where mid=?";
	String delall_mRice = "delete from mahajan_rice where mid=?";
	String delall_mBilling = "delete from mahajan_billing where mid=?";

	ConnectionFactory obj = new ConnectionFactory();
	Connection cn = obj.getCon();

	public void InsertMahajanMain(MahajanMainDTO mmdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanMain);
			ps.setString(1, mmdto.getMid());
			ps.setString(2, mmdto.getMname());
			ps.setString(3, mmdto.getTotal_bill());
			ps.setString(4, mmdto.getTotal_pay());
			ps.setString(5, mmdto.getTotal_due());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertMahajanRice(MahajanRiceDTO mrdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanRice);
			ps.setString(1, mrdto.getSdate());
			ps.setString(2, mrdto.getMid());
			ps.setString(3, mrdto.getRice_id());
			ps.setString(4, mrdto.getBag());
			ps.setString(5, mrdto.getNofbag());
			ps.setString(6, mrdto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(mrdto.getNofbag());

			ps = cn.prepareStatement(select_stockRice);
			ps.setString(1, mrdto.getRice_id());
			ps.setString(2, mrdto.getBag());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevNofbag = Integer.parseInt(rs.getString(1));
				int number = nnofbag + prevNofbag;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(upd_stockRice);
				ps.setString(1, snum);
				ps.setString(2, mrdto.getRice_id());
				ps.setString(3, mrdto.getBag());
				ps.executeUpdate();
			}
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void InsertMahajanBill(MahajanBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.setString(3, mbdto.getTotal_bill());
			ps.setString(4, mbdto.getPayment());
			ps.setString(5, mbdto.getDue());
			ps.executeUpdate();

			String bill = mbdto.getTotal_bill();
			String pay = mbdto.getPayment();
			String due = mbdto.getDue();
			double billc, payc, duec;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);
			duec = Double.parseDouble(due);

			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
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

				ps = cn.prepareStatement(mahajanmain_upd);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, mbdto.getMid());
			}
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void ExPaymentMahajan(MahajanBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_mahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.setString(3, mbdto.getTotal_bill());
			ps.setString(4, mbdto.getPayment());
			ps.setString(5, mbdto.getDue());
			ps.executeUpdate();

			String bill = mbdto.getTotal_bill();
			String pay = mbdto.getPayment();

			double billc, payc;
			billc = Double.parseDouble(bill);
			payc = Double.parseDouble(pay);

			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
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

				ps = cn.prepareStatement(mahajanmain_upd);
				ps.setString(1, fnBill);
				ps.setString(2, fnPay);
				ps.setString(3, fnDue);
				ps.setString(4, mbdto.getMid());
			}
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	public void DelMahajanRiceBill(MahajanBillDTO mbdto) {

		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill, fpay, fdue;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue - ddue;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(mahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_MahajanRice);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
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
					int number = prevNofbag - nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(upd_stockRice);
					ps.setString(1, snum);
					ps.setString(2, sdto.getRice_id());
					ps.setString(3, sdto.getBag());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(delete_MahajanRice);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public void ExPaymentDel(MahajanBillDTO mbdto) {
		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_mahajan);
			ps.setString(1, mbdto.getMid());
			rs = ps.executeQuery();
			double mbill = 0, mpay = 0, mdue = 0;
			if (rs.next()) {
				mbill = Double.parseDouble(rs.getString(1));
				mpay = Double.parseDouble(rs.getString(2));
				mdue = Double.parseDouble(rs.getString(3));
			}
			double fbill, fpay, fdue;
			fbill = mbill - dbill;
			fpay = mpay - dpay;
			fdue = mdue + dpay;

			String fbillc, fpayc, fduec;
			fbillc = String.valueOf(fbill);
			fpayc = String.valueOf(fpay);
			fduec = String.valueOf(fdue);

			ps = cn.prepareStatement(mahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	public int MidCheck(String mid) {

		int flag=0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_mahajanMain);
			while (rs.next()) {
				if (rs.getString(1).equals(mid)) {
					flag= 1;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;

	}
	public ArrayList getMid() {
		ArrayList arr=new ArrayList();
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_mahajanMain);
			while(rs.next())
			{
				arr.add(rs.getString(1));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return arr;
	}

	public int CheckDateMid(String sdate, String mid) {

		int flag=1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_midDate);
			while (rs.next()) {

				if (rs.getString(1).equals(sdate) && rs.getString(2).equals(mid)) {
					flag=0;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;

	}

	public int CheckmahajanRiceDuplicate(String sdate, String mid, String rice_id, String bag) {
		int flag=1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(sdate) && rs.getString(2).equals(mid) && rs.getString(3).equals(rice_id)
						&& rs.getString(4).equals(bag)) {
					flag=0;
					break;
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourceST(rs, st, cn);
		return flag;
	}

	public void DelMahajanAllDetails(String mid) {

		try {
			ps = cn.prepareStatement(delall_mBilling);
			ps.setString(1, mid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(delall_mRice);
			ps.setString(1, mid);
			ps.executeUpdate();
//			cn.commit();

			ps = cn.prepareStatement(delall_mMain);
			ps.setString(1, mid);
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

}