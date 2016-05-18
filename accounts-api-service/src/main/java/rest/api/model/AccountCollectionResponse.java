package rest.api.model;

import java.util.ArrayList;
import java.util.List;

public class AccountCollectionResponse {
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		if (this.accounts == null) {
			this.accounts = new ArrayList<>();
		}
		this.accounts.add(account);
	}
		
}
