package rest.core.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import rest.core.model.PaymentDetail;
import rest.core.model.PaymentDetailCollection;

@Service
public class PaymentsServiceImpl implements PaymentsService {
	
	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Environment environment;

	@Override
	public PaymentDetailCollection getPaymentDetails(String accountNumber) {
		
		PaymentDetailCollection paymentDetail = new PaymentDetailCollection();
		paymentDetail.setAccountNumber(accountNumber);
		paymentDetail.setLastPaymentAmount(100);
		paymentDetail.setLastPaymentDate(new Date());
		
		paymentDetail.setPaymentDetails(this.getPaymentDetailsAsList());
		
		return paymentDetail;
	}
	
	private List<PaymentDetail> getPaymentDetailsAsList(){
		ServiceInstance localInstance = client.getLocalServiceInstance();
		String str = environment.getProperty("spring.application.name") + ":" + localInstance.getServiceId() + ":"
				+ localInstance.getHost() + ":" + localInstance.getPort();
		List<PaymentDetail> paymentDetails = Arrays.asList(
			new PaymentDetail(10, 20, new Date(), str),
			new PaymentDetail(11, 22, new Date(), str),
			new PaymentDetail(12, 23, new Date(), str));
		
		return paymentDetails;
	}
}
