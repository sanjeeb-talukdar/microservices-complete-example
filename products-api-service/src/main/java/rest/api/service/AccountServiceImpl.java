package rest.api.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import rest.api.model.Account;
import rest.api.model.AccountComposite;
import rest.api.model.AccountCompositeCollection;
import rest.api.model.PaymentDetail;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private RestTemplate accountRestTemplate;

	@Override
	public List<Account> getAccounts(String customerId) {
		List<Account> accounts = Arrays.asList(new Account("1", "Account 1"),
				new Account("2", "Account 2"),
				new Account("3", "Account 3"));
		
		return accounts;
	}
	
	
	@Override
	public Account getAccount(String accountId) {
		return new Account(accountId, "My Account Details");
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackAccounts", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public AccountCompositeCollection getAccountComposites(String customerId) {
		AccountCompositeCollection accountCompositeCollection = accountRestTemplate.getForObject("http://accounts-composite-service/accounts", AccountCompositeCollection.class);
		return accountCompositeCollection;
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackAccount", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public AccountComposite getAccountComposite(String accountNumber) {
		AccountComposite accountComposite = accountRestTemplate.getForObject("http://accounts-composite-service/accounts/"+accountNumber, AccountComposite.class);
		return accountComposite;
	}
	
	@SuppressWarnings("unused")
	private AccountCompositeCollection getFallbackAccounts(String customerId) {
		// Get the default comments
		AccountCompositeCollection accounts = new AccountCompositeCollection();
		accounts.addAccount(
			new AccountComposite(
				"Fallback Accounts", "Nick Name", 123, new Date(), 
				Arrays.asList(
				new PaymentDetail(10, 20, new Date()),
				new PaymentDetail(11, 22, new Date()),
				new PaymentDetail(12, 23, new Date())))
		);

		return accounts;
	}
	
	
	@SuppressWarnings("unused")
	private AccountComposite getFallbackAccount(String accountNumber) {
		return new AccountComposite(
				"Fallback Acc 1", "Individual Account", 123, new Date(), 
				Arrays.asList(
				new PaymentDetail(10, 20, new Date()),
				new PaymentDetail(11, 22, new Date()),
				new PaymentDetail(12, 23, new Date())));
	}

}
