package master.DAO;

import java.sql.*;
import java.util.*;

import master.DTO.MahajanOilBillDTO;
import master.DTO.MahajanOilDTO;
import master.DTO.OilMahajanMainDTO;
import master.DTO.StockOilDTO;
import master.utilities.ConnectionFactory;

public class OilMahajanDAO {
	
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String insert_oilmahajanBill = "insert into oilmahajan_billing values(?,?,?,?,?)";
	String insert_mahajanOil = "insert into mahajan_oil values(?,?,?,?,?,?)";
	String insert_oilmahajanMain = "insert into oilmahajan_main values(?,?,?,?,?)";
	
	String oilmahajanmain_upd = "update oilmahajan_main set total_bill=?,total_pay=?,total_due=? where mid=?";
	String select_oilmahajan = "select total_bill,total_pay,total_due from oilmahajan_main where mid=?";
	
	String del_MahajanOilBill = "delete from oilmahajan_billing where sdate=? and mid=?";
	
	String select_MahajanOil = "select oil_id,category,quantity from mahajan_oil where sdate=? and mid=?";
	String delete_MahajanOil = "delete from mahajan_oil where sdate=? and mid=?";
	
	String upd_stockOil = "update stock_oil set quantity=? where oil_id=? and category=?";
	String select_stockOil = "select quantity from stock_oil where oil_id=? and category=?";
	
	String select_OilMahajanBill = "select total_bill,payment,due from oilmahajan_billing where sdate=? and mid=?";
	
	String select_oilmahajanMain = "select mid from oilmahajan_main";
	
	String select_oilmidDate = "select sdate,mid from oilmahajan_billing";
	String duplicateCheck = "select sdate,mid,oil_id,category from mahajan_oil";
	
	String delall_mMain = "delete from oilmahajan_main where mid=?";
	String delall_mRice = "delete from mahajan_oil where mid=?";
	String delall_mBilling = "delete from oilmahajan_billing where mid=?";
	
	ConnectionFactory obj=new ConnectionFactory();
	Connection cn=obj.getCon();
	
	public void InsertMahajanMain(OilMahajanMainDTO mmdto) {

		try {
			ps = cn.prepareStatement(insert_oilmahajanMain);
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
	public void InsertMahajanOil(MahajanOilDTO modto) {

		try {
			ps = cn.prepareStatement(insert_mahajanOil);
			ps.setString(1, modto.getSdate());
			ps.setString(2, modto.getMid());
			ps.setString(3, modto.getOil_id());
			ps.setString(4, modto.getCategory());
			ps.setString(5, modto.getQuan());
			ps.setString(6, modto.getPrice());
			ps.executeUpdate();

			int nnofbag = Integer.parseInt(modto.getQuan());

			ps = cn.prepareStatement(select_stockOil);
			ps.setString(1, modto.getOil_id());
			ps.setString(2, modto.getCategory());
			rs = ps.executeQuery();
			if (rs.next()) {

				int prevQuan = Integer.parseInt(rs.getString(1));
				int number = nnofbag + prevQuan;
				String snum = String.valueOf(number);

				ps = cn.prepareStatement(upd_stockOil);
				ps.setString(1, snum);
				ps.setString(2, modto.getOil_id());
				ps.setString(3, modto.getCategory());
				ps.executeUpdate();
			}
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	
	public void InsertOilMahajanBill(MahajanOilBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_oilmahajanBill);
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

			ps = cn.prepareStatement(select_oilmahajan);
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

				ps = cn.prepareStatement(oilmahajanmain_upd);
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
	public void ExPaymentMahajan(MahajanOilBillDTO mbdto) {

		try {
			ps = cn.prepareStatement(insert_oilmahajanBill);
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

			ps = cn.prepareStatement(select_oilmahajan);
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

				ps = cn.prepareStatement(oilmahajanmain_upd);
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
	
	public void DelMahajanOilBill(MahajanOilBillDTO mbdto) {

		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_OilMahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_oilmahajan);
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

			ps = cn.prepareStatement(oilmahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanOilBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(select_MahajanOil);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
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
				ps = cn.prepareStatement(select_stockOil);
				ps.setString(1, sdto.getOil_id());
				ps.setString(2, sdto.getCategory());
				rs = ps.executeQuery();

				int nofbagc = Integer.parseInt(sdto.getQuan());
				if (rs.next()) {

					int prevNofbag = Integer.parseInt(rs.getString(1));
					int number = prevNofbag - nofbagc;
					String snum = String.valueOf(number);

					ps = cn.prepareStatement(upd_stockOil);
					ps.setString(1, snum);
					ps.setString(2, sdto.getOil_id());
					ps.setString(3, sdto.getCategory());
					ps.executeUpdate();
				}
			}

			ps = cn.prepareStatement(delete_MahajanOil);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();
//			cn.commit();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}
	
	public void OilExPaymentDel(MahajanOilBillDTO mbdto) {
		ArrayList arr = new ArrayList();
		try {
			ps = cn.prepareStatement(select_OilMahajanBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			rs = ps.executeQuery();
			double dbill = 0, dpay = 0, ddue = 0;
			if (rs.next()) {
				dbill = Double.parseDouble(rs.getString(1));
				dpay = Double.parseDouble(rs.getString(2));
				ddue = Double.parseDouble(rs.getString(3));
			}
			ps = cn.prepareStatement(select_oilmahajan);
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

			ps = cn.prepareStatement(oilmahajanmain_upd);
			ps.setString(1, fbillc);
			ps.setString(2, fpayc);
			ps.setString(3, fduec);
			ps.setString(4, mbdto.getMid());
			ps.executeUpdate();

			ps = cn.prepareStatement(del_MahajanOilBill);
			ps.setString(1, mbdto.getSdate());
			ps.setString(2, mbdto.getMid());
			ps.executeUpdate();

			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		obj.releaseConnectionSourcePS(rs, ps, cn);
	}

	
	public int CheckDateMid(String sdate, String mid) {

		int flag=1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_oilmidDate);
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
	
	public int MidCheck(String mid) {

		int flag=0;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(select_oilmahajanMain);
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
			rs = st.executeQuery(select_oilmahajanMain);
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
	public int CheckmahajanOilDuplicate(String sdate, String mid, String oil_id, String category) {
		int flag=1;
		try {
			st = cn.createStatement();
			rs = st.executeQuery(duplicateCheck);
			while (rs.next()) {

				if (rs.getString(1).equals(sdate) && rs.getString(2).equals(mid) && rs.getString(3).equals(oil_id)
						&& rs.getString(4).equals(category)) {
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
