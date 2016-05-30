package com.pricing.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.pricing.core.data.Price;
import com.pricing.core.data.repository.PriceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PricingCoreApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Transactional
public class PricingCoreApplictionTest {
	@Value("${local.server.port}")
	private int port;
	
	@Autowired
	private PriceRepository PriceRepository;

	private RestTemplate template = new TestRestTemplate();

	@Test
	public void findByIdTest(){
		System.out.println(PriceRepository.findAll());
		Price[] response = template.getForObject("http://localhost:" + port + "/product/1/price",
				Price[].class);
		assertNotNull(response);
		System.out.println(response[0].getProductId());
		assertEquals(Long.valueOf(1), response[0].getProductId());
		
	}
}
