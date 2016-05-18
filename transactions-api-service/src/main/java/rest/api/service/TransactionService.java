package rest.api.service;

import rest.api.model.Transaction;
import rest.api.model.TransactionCollectionResponse;

public interface TransactionService {

	public Transaction getTransactionDetail(String accountNumber, long transactionId);
	
	public TransactionCollectionResponse getTransactions(String accountId);
}
