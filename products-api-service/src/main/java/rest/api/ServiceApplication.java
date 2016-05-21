package rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class ServiceApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
	
	@Autowired
	public void setEnvironment(Environment e) {
		System.out.println(e.getProperty("configuration.projectName"));
	}

}
