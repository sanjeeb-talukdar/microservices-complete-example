package rest.api.service;

import rest.api.model.Transaction;
import rest.api.model.TransactionCollectionResponse;

public interface TransactionService {

	public Transaction transactionDetail(String accountNumber, long transactionId);
	
	public TransactionCollectionResponse listTransactions4Account(String accountId);
}
