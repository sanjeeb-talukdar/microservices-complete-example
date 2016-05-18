package rest.composite.model;

import java.util.ArrayList;
import java.util.List;

public class AccountCompositeCollection {
	private List<AccountComposite> accounts;

	public List<AccountComposite> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountComposite> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(AccountComposite account) {
		if (this.accounts == null) {
			this.accounts = new ArrayList<>();
		}
		this.accounts.add(account);
	}
}
