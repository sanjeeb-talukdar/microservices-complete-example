package rest.api.model;

public class Account {
	
	public static final String JP_ACCOUNTNUMBER = "accountNumber";
	public static final String JP_NICKNAME = "nickName";
		
	private final String accountNumber;
	private final String nickName;
	
	public Account(String accountNumber, String nickName){
		this.accountNumber = accountNumber;
		this.nickName = nickName;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getNickName() {
		return nickName;
	}
	
}
