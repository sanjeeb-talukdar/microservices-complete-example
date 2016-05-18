package rest.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.core.model.PaymentDetailCollection;
import rest.core.service.PaymentsService;

@RestController
public class PaymentsController {
	
	@Autowired
	private PaymentsService paymentsService;

    @RequestMapping(value="/accounts/{accountNumber}/payment-details", method = RequestMethod.GET, headers = "Accept=application/json")
    public PaymentDetailCollection getPaymentDetails(
    		@PathVariable("accountNumber") String accountNumber) {
    	return paymentsService.getPaymentDetails(accountNumber);
    }
    
}
