package master.DTO;

public class CustomerBillDTO {
	
	String cdate;
	String cid;
	String total_bill;
	String payment;
	String due;
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getTotal_bill() {
		return total_bill;
	}
	public void setTotal_bill(String total_bill) {
		this.total_bill = total_bill;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	
	

}
