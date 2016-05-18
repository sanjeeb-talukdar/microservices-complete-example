package rest.core.model;

import java.io.Serializable;

public class Transaction implements Serializable{
	
	private static final long serialVersionUID = -3224430790044307427L;
	
	private long transactionId;
	private String accountNumber;
	private String fromAccount;
	private String transactionDate;
	private double transactionAmount;
	
	public Transaction(){}

	public Transaction(long transactionId, String accountNumber, String fromAccount,
			String transactionDate, double transactionAmount) {
		this.transactionId = transactionId;
		this.accountNumber = accountNumber;
		this.fromAccount = fromAccount;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
	}
	

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

}