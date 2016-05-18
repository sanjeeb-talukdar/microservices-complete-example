package rest.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rest.core.model.Account;
import rest.core.model.AccountCollectionResponse;
import rest.core.service.AccountCoreService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountCoreService accoutService;

    @RequestMapping(value="/accounts", method = RequestMethod.GET, headers = "Accept=application/json")
    public AccountCollectionResponse getAccounts(
    		@RequestParam(value="customerNumber", defaultValue="123") String customerNumber) {
    	
    	AccountCollectionResponse response = new AccountCollectionResponse();
    	response.setAccounts(accoutService.getAccounts(customerNumber));
    	return response;
    }
    
    @RequestMapping("/accounts/{accountNumber}")
    public Account getAccountDetail(@PathVariable("accountNumber") String accountNumber) {
        return accoutService.getAccount(accountNumber);
    }

}
