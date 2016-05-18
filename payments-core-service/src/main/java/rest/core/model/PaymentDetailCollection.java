package rest.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentDetailCollection {
	
	private String accountNumber;
	private double lastPaymentAmount;
	private Date lastPaymentDate;
	
	private List<PaymentDetail> paymentDetails;
		
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

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public List<PaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
	
	public void addPaymentDetail(PaymentDetail paymentDetail) {
		if (this.paymentDetails == null) {
			this.paymentDetails = new ArrayList<>();
		}
		this.paymentDetails.add(paymentDetail);
	}
	
}
