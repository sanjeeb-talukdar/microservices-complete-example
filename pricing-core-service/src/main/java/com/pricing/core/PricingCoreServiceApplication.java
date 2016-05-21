package com.pricing.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class PricingCoreServiceApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(PricingCoreServiceApplication.class, args);
    }
	
}
