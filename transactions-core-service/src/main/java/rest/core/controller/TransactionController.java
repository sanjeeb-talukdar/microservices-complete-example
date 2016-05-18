package rest.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.core.model.Transaction;
import rest.core.model.TransactionCollectionResponse;
import rest.core.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

    @RequestMapping(value="/accounts/{accountNumber}/transactions", method = RequestMethod.GET, headers = "Accept=application/json")
    public TransactionCollectionResponse getTransactions(
    		@PathVariable("accountNumber") String accountNumber) {
    	
    	TransactionCollectionResponse response = new TransactionCollectionResponse();
    	response.setTransactions(transactionService.getTransactions(accountNumber));
    	return response;
    }
    
    @RequestMapping("/accounts/{accountNumber}/transactions/{transactionId}")
    public Transaction getTransactionDetail(
    		@PathVariable("accountNumber") String accountNumber,
    		@PathVariable("transactionId") long transactionId
    		) {
        return transactionService.getTransactionDetail(transactionId);
    }
    
}
