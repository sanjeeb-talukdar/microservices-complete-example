package rest.composite.service;

import java.util.List;

import rest.composite.model.AccountComposite;

public interface AccountCompositeService {
	
	public AccountComposite getAccount(String accountNumber);
	
	public List<AccountComposite> getAccounts(String customerId);
}
