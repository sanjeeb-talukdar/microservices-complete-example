package rest.composite.model;

public class Account {
	
	private String accountNumber;
	private String nickName;
	private String accountType;
	private double currentBalance;
	private String text;
	
	public Account(){}
	
	public Account(String accountNumber, String nickName, String accountType, double currentBalance, String text){
		this.accountNumber = accountNumber;
		this.nickName = nickName;
		this.accountType = accountType;
		this.currentBalance = currentBalance;
		this.text = text;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public double getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	
}
