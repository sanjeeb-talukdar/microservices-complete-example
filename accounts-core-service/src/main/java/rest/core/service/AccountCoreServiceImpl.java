package rest.core.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import rest.core.model.Account;

@Service
public class AccountCoreServiceImpl implements AccountCoreService {

	
	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Environment environment;
	
	@Override
	public Account getAccount(String accountNumber) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		return new Account(accountNumber, "My Account Details", "SAVINGS", 1000, str);
	}

	@Override
	public List<Account> getAccounts(String customerNumber) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		List<Account> accounts = Arrays.asList(
				new Account("1", "Account 1", "SAVINGS", 100, str),
				new Account("2", "Account 2", "CHECKING", 200, str),
				new Account("3", "Account 3", "SAVINGS", 300, str));
		
		return accounts;
	}

}
