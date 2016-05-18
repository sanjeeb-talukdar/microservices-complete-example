package rest.api.service;

import rest.api.model.AccountComposite;
import rest.api.model.AccountCompositeCollection;

public interface AccountService {
	AccountCompositeCollection getAccountComposites(String customerId);
	AccountComposite getAccountComposite(String accountNumber);
}
