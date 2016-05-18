package rest.composite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.composite.model.AccountComposite;
import rest.composite.model.AccountCompositeCollection;
import rest.composite.service.AccountCompositeService;

@RestController
public class AccountCompositeController {
	
	@Autowired
	private AccountCompositeService accoutService;

    @RequestMapping(value="/accounts", method = RequestMethod.GET, headers = "Accept=application/json")
    public AccountCompositeCollection getAccounts() {
    	
    	AccountCompositeCollection response = new AccountCompositeCollection();
    	response.setAccounts(accoutService.getAccounts("123"));
    	return response;
    }
    
    @RequestMapping("/accounts/{accountNumber}")
    public AccountComposite getAccountDetail(
    		@PathVariable("accountNumber") String accountNumber) {
        return accoutService.getAccount(accountNumber);
    }
    
}
