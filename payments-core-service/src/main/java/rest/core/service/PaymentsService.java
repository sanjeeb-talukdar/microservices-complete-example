package rest.core.service;

import rest.core.model.PaymentDetailCollection;

public interface PaymentsService { 
	
	public PaymentDetailCollection getPaymentDetails(String accountNumber);
	
}
