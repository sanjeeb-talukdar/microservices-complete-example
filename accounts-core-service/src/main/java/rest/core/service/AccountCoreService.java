package rest.core.service;

import java.util.List;

import rest.core.model.Account;

public interface AccountCoreService {
	
	public Account getAccount(String accountNumber);
	
	public List<Account> getAccounts(String customerNumber);

}
