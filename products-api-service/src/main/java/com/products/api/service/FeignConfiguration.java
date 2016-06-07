package com.products.api.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import feign.Feign;
import feign.Request;
import feign.hystrix.HystrixFeign;

@Configuration
public class FeignConfiguration {
	    @Bean
		public Feign.Builder feignBuilder() {
			return HystrixFeign.builder();
		}
	    
	    @Bean
		public Request.Options requestOptions(ConfigurableEnvironment env) {
			int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 6000000);
			int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 6000000);
			return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
		}
	    
	}