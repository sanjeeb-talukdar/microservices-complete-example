package rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableCircuitBreaker
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
@Configuration
@EnableHystrix
@EnableFeignClients
@EnableResourceServer
public class TransactionsApiServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionsApiServiceApplication.class, args);
	}

	@Autowired
	public void setEnvironment(Environment e) {
		System.out.println("####### Environment:" + e.getProperty("configuration.projectName"));
	}

}
