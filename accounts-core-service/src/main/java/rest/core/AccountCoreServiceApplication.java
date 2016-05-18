package rest.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EnableHystrix
public class AccountCoreServiceApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(AccountCoreServiceApplication.class, args);
    }
	
}
