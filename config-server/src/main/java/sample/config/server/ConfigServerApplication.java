/**
 * 
 */
package sample.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * The Main Spring Boot Application class.<br>
 * <br>
 * 
 * The {@link EnableConfigServer} annotation defines that this application will
 * serve as the REST based API for providing external configuration. <br>
 * <br>
 * 
 * The external repository from where the configuration will be picked up is
 * defined in the {@linkplain application.yml} file.
 * 
 */

@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
@EnableHystrix
public class ConfigServerApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
