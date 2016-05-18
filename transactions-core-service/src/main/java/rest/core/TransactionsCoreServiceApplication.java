package rest.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan
@EnableHystrix
public class TransactionsCoreServiceApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(TransactionsCoreServiceApplication.class, args);
    }
	
	@Autowired
	public void setEnvironment(Environment e) {
		System.out.println("####### Environment:"+e.getProperty("configuration.projectName"));
	}

}
