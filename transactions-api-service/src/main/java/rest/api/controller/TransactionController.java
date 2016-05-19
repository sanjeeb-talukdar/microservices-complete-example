package rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.api.model.Transaction;
import rest.api.model.TransactionCollectionResponse;
import rest.api.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

    @RequestMapping(value="/accounts/{accountNumber}/transactions", method = RequestMethod.GET, headers = "Accept=application/json")
    public TransactionCollectionResponse getTransactions(
    		@PathVariable("accountNumber") String accountNumber) {
    	
    	/*TransactionCollectionResponse response = new TransactionCollectionResponse();
    	response.setTransactions(transactionService.getTransactions(accountNumber));*/
    	return transactionService.listTransactions4Account(accountNumber);
    }
    
    @RequestMapping("/accounts/{accountNumber}/transactions/{transactionId}")
    public Transaction getTransactionDetail(
    		@PathVariable("accountNumber") String accountNumber,
    		@PathVariable("transactionId") long transactionId
    		) {
        return transactionService.transactionDetail(accountNumber, transactionId);
    }
    
}
