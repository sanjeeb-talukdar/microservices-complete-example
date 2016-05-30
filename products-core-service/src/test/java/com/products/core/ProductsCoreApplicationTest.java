package com.products.core;

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

import com.products.core.data.Catalogue;
import com.products.core.data.Product;
import com.products.core.data.repository.CatalogueRepository;
import com.products.core.data.repository.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProductsCoreApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@Transactional
public class ProductsCoreApplicationTest {
	@Value("${local.server.port}")
	private int port;
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CatalogueRepository catalogueRepository;

	private RestTemplate template = new TestRestTemplate();

	@Test
	public void findByIdProductTest(){ 
		System.out.println(productRepository.findAll());
		Product[] response = template.getForObject("http://localhost:" + port + "/product/",
				Product[].class);
		assertNotNull(response);
		System.out.println(response[0].getId());
		assertEquals(Long.valueOf(1), response[0].getId());
		
	}
	@Test
	public void findByIdCatalogueTest(){
		System.out.println(catalogueRepository.findAll());
		Catalogue[] response = template.getForObject("http://localhost:" + port + "/catalogue/",
				Catalogue[].class);
		assertNotNull(response);
		System.out.println(response[0].getId());
		assertEquals(Integer.valueOf(1), response[0].getId());
		
	}
}
