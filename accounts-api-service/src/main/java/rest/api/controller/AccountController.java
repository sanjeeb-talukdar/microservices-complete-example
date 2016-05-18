package rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.api.model.AccountComposite;
import rest.api.model.AccountCompositeCollection;
import rest.api.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accoutService;

	@RequestMapping(value="/accounts", method = RequestMethod.GET, headers = "Accept=application/json")
    public AccountCompositeCollection getAccountComposites(
    		@RequestParam(value="customerNumber", defaultValue="123") String customerNumber) {
    	
		AccountCompositeCollection accountCompositeCollection = accoutService.getAccountComposites(customerNumber);
    	return accountCompositeCollection;
    }
	
	@RequestMapping(value="/accounts/{accountNumber}", method = RequestMethod.GET, headers = "Accept=application/json")
    public AccountComposite getAccountComposite(
    		@PathVariable("accountNumber") String accountNumber) {
    	
		AccountComposite accountComposite = accoutService.getAccountComposite(accountNumber);
    	return accountComposite;
    }
    
}
