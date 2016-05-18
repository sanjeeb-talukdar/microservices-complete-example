package rest.composite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rest.composite.model.Account;
import rest.composite.model.AccountCollectionResponse;
import rest.composite.model.AccountComposite;
import rest.composite.model.PaymentDetailCollection;

@Service
public class AccountCompositeServiceImpl implements AccountCompositeService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public AccountComposite getAccount(String accountNumber) {
		Account account = this.getCoreAccount(accountNumber);
		AccountComposite accountComposite = new AccountComposite();
		accountComposite.setAccountNumber(account.getAccountNumber());
		accountComposite.setNickName(account.getNickName());
		accountComposite.setText(account.getText());
		
		PaymentDetailCollection paymentDetailCollection = this.getPaymentDetails(accountNumber);
		accountComposite.setPaymentDetails(paymentDetailCollection.getPaymentDetails());
		accountComposite.setLastPaymentAmount(paymentDetailCollection.getLastPaymentAmount());
		accountComposite.setLastPaymentDate(paymentDetailCollection.getLastPaymentDate());
		
		return accountComposite;
	}

	@Override
	public List<AccountComposite> getAccounts(String accountNumber) {
		
		List<AccountComposite> accountComposites = new ArrayList<AccountComposite>();
		AccountCollectionResponse accountCollectionResponse = this.getCoreAccounts(accountNumber);
		
		for(Account account:accountCollectionResponse.getAccounts()){
			
			AccountComposite accountComposite = new AccountComposite();
			accountComposite.setAccountNumber(account.getAccountNumber());
			accountComposite.setNickName(account.getNickName());
			accountComposite.setText(account.getText());
			PaymentDetailCollection paymentDetail = this.getPaymentDetails(account.getAccountNumber());
			accountComposite.setPaymentDetails(paymentDetail.getPaymentDetails());
			accountComposite.setLastPaymentAmount(paymentDetail.getLastPaymentAmount());
			accountComposite.setLastPaymentDate(paymentDetail.getLastPaymentDate());
			
			accountComposites.add(accountComposite);
		}
		
		return accountComposites;
	}
	
	private PaymentDetailCollection getPaymentDetails(String accountNumber){
		PaymentDetailCollection paymentDetail = restTemplate.getForObject("http://payments-core-service/accounts/"+accountNumber+"/payment-details", PaymentDetailCollection.class);
		return paymentDetail;
	}
	
	private Account getCoreAccount(String accountNumber){
		Account account = restTemplate.getForObject("http://accounts-core-service/accounts/"+accountNumber, Account.class);
		return account;
	}
	
	private AccountCollectionResponse getCoreAccounts(String accountNumber){
		AccountCollectionResponse accountCollectionResponse = restTemplate.getForObject("http://accounts-core-service/accounts", AccountCollectionResponse.class);
		return accountCollectionResponse;
	}
	

}
