package rest.core.model;

import java.util.ArrayList;
import java.util.List;

public class TransactionCollectionResponse {
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		if (this.transactions == null) {
			this.transactions = new ArrayList<>();
		}
		this.transactions.add(transaction);
	}
}
