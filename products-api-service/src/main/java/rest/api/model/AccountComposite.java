package rest.api.model;

import java.util.Date;
import java.util.List;

public class AccountComposite {
	
	private String accountNumber;
	private String nickName;
	private double lastPaymentAmount;
	private Date lastPaymentDate;
	private List<PaymentDetail> paymentDetails;

	public AccountComposite(){
	}
	
	public AccountComposite(String accountNumber, String nickName, 
			double lastPaymentAmount, Date lastPaymentDate,
			List<PaymentDetail> paymentDetails){
		
		this.accountNumber = accountNumber;
		this.nickName = nickName;
		this.lastPaymentAmount = lastPaymentAmount;
		this.lastPaymentDate = lastPaymentDate;
		this.paymentDetails = paymentDetails;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public double getLastPaymentAmount() {
		return lastPaymentAmount;
	}
	public void setLastPaymentAmount(double lastPaymentAmount) {
		this.lastPaymentAmount = lastPaymentAmount;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	
	
}
