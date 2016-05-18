## Overview
This is a composite service for an account and payment details. It exposes REST endpoints that are used to provide account and payment functionality.

## Pre-requisites
Projects that need to be started before
* sample config server - For pulling the configuration information
* discovery-server - For starting the Eureka server to register all the services (api, core and composite)

## Running the application
* Build the application by running the command: gradle clean build
* Run the application by running the command: java -jar build/libs/accounts-composite-service-1.0.0.jar

Invoke the service by using the below urls
* http://localhost:8084/accounts/123
* http://localhost:8084/accounts/

##External Configuration
The project derives it's configuration from the config server service. We have defined the spring.cloud.config.uri in the bootstrap.yml file and that tells the application where to pick up the external confiurations. In our case, the URL points to the running config server server (http://localhost:8888). 

A Spring Cloud application operates by creating a "bootstrap" context, which is a parent context for the main application. This bootstrap context loads properties from external sources (the config-server) and decrypts the properties if required.

The bootstrap context for external configuration is located by convention at bootstrap.yml whereas the internal configuration is located by convention at application.yml. Note that you can also have .properties file instead of .yml files.

Important dependencies in classpath
* spring-cloud-config-client dependency so that the application can comsume the config server
* spring-cloud-starter-eureka dependecy to register the service in discovery server 
* spring-cloud-starter-hystrix dependecy to enable the circuit breaker for the service

