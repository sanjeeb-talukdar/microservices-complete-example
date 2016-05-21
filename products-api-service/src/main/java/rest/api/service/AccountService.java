package rest.api.service;

import java.util.List;

import rest.api.model.Account;
import rest.api.model.AccountComposite;
import rest.api.model.AccountCompositeCollection;


public interface AccountService {
	
	public List<Account> getAccounts(String customerId);
	
	public Account getAccount(String accountNumber);
	
	public AccountCompositeCollection getAccountComposites(String customerId); 
	
	public AccountComposite getAccountComposite(String accountNumber);

}
