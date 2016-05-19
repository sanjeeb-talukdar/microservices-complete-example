package rest.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import rest.api.model.Transaction;
import rest.api.model.TransactionCollectionResponse;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Environment environment;
	
	@FeignClient("transactions-core-service")
	private interface TransactionsCoreService {
		@RequestMapping(value = "/accounts/{accountNumber}/transactions", method = RequestMethod.GET, headers = "Accept=application/json")
		TransactionCollectionResponse getTransactions(@RequestParam("accountNumber") String accountNumber);

		@RequestMapping(value = "/accounts/{accountNumber}/transactions/{transactionId}", method = RequestMethod.GET, headers = "Accept=application/json")
		Transaction getTransactionDetail(@RequestParam("accountNumber") String accountNumber,
				@RequestParam("transactionId") long transactionId);
	}

	@Autowired
	private TransactionsCoreService transactionsCoreService;

	@HystrixCommand(fallbackMethod = "getFallbackTransactionDetail", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public Transaction transactionDetail(String accountNumber, long transactionId) {
		return transactionsCoreService.getTransactionDetail(accountNumber, transactionId);
	}

	@HystrixCommand(fallbackMethod = "getFallbackTransactions", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public TransactionCollectionResponse listTransactions4Account(String accountNumber) {
		return transactionsCoreService.getTransactions(accountNumber);
	}

	public TransactionCollectionResponse getFallbackTransactions(String accountNumber) {
		TransactionCollectionResponse response = new TransactionCollectionResponse();
		List<Transaction> transactions = new ArrayList<Transaction>();
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		for (int i = 0; i < 3; i++) {
			Transaction transaction = new Transaction(i + 1, accountNumber, str + i, "2014-12-12", 10);
			transactions.add(transaction);
		}
		response.setTransactions(transactions);
		return response;
	}

	public Transaction getFallbackTransactionDetail(String accountNumber, long transactionId) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		return new Transaction(transactionId, accountNumber, str, "2014-12-12", 123.00);
	}

}
