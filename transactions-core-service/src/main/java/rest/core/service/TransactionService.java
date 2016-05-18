package rest.core.service;

import java.util.List;

import rest.core.model.Transaction;

public interface TransactionService {

	public Transaction getTransactionDetail(long transactionId);
	
	public List<Transaction> getTransactions(String accountId);
}
