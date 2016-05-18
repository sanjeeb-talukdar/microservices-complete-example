package rest.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import rest.core.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Environment environment;

	@Override
	public Transaction getTransactionDetail(long transactionId) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();

		Transaction transaction = new Transaction(transactionId, "123", "FromAcc-Detail: " + str, "2014-12-12",
				transactionId);
		return transaction;
	}

	@Override
	public List<Transaction> getTransactions(String accountId) {
		ServiceInstance localInstance = client.getLocalServiceInstance();

		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		List<Transaction> transactions = new ArrayList<Transaction>();
		for (int i = 0; i < 4; i++) {
			Transaction transaction = new Transaction(i + 1, accountId, "FromAcc-" + i + ": " + str, "2014-12-12", 10);
			transactions.add(transaction);
		}

		return transactions;
	}

}
