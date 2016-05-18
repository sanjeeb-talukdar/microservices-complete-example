package rest.api.model;

public class Account {
	
	public static final String JP_ACCOUNTNUMBER = "accountNumber";
	public static final String JP_NICKNAME = "nickName";
		
	private final String accountNumber;
	private final String nickName;
	private final String text;
	
	public Account(String accountNumber, String nickName, String text){
		this.accountNumber = accountNumber;
		this.nickName = nickName;
		this.text = text;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getNickName() {
		return nickName;
	}

	public String getText() {
		return text;
	}
	
}
