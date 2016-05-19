package rest.api.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import rest.api.model.AccountComposite;
import rest.api.model.AccountCompositeCollection;
import rest.api.model.PaymentDetail;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate accountRestTemplate;
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private Environment environment;
	
	@HystrixCommand(fallbackMethod = "getFallbackAccounts", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public AccountCompositeCollection listAllAccounts() {
		AccountCompositeCollection accountCompositeCollection = accountRestTemplate.getForObject("http://accounts-composite-service/accounts", AccountCompositeCollection.class);
		return accountCompositeCollection;
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackAccount", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	@Override
	public AccountComposite accountDetails(String accountNumber) {
		AccountComposite accountComposite = accountRestTemplate.getForObject("http://accounts-composite-service/accounts/"+accountNumber, AccountComposite.class);
		return accountComposite;
	}
	
	@SuppressWarnings("unused")
	private AccountCompositeCollection getFallbackAccounts() {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		// Get the default comments
		AccountCompositeCollection accounts = new AccountCompositeCollection();
		accounts.addAccount(
			new AccountComposite(
				"Fallback Accounts", "Nick Name", 123, new Date(), 
				Arrays.asList(
				new PaymentDetail(10, 20, new Date(), str),
				new PaymentDetail(11, 22, new Date(), str),
				new PaymentDetail(12, 23, new Date(), str)), str)
		);

		return accounts;
	}
	
	
	@SuppressWarnings("unused")
	private AccountComposite getFallbackAccount(String accountNumber) {
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		return new AccountComposite(
				"Fallback Acc 1", "Individual Account", 123, new Date(), 
				Arrays.asList(
				new PaymentDetail(10, 20, new Date(), str),
				new PaymentDetail(11, 22, new Date(), str),
				new PaymentDetail(12, 23, new Date(), str)), str);
	}

}
