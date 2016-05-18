## Microservices Architecture
This is an example of Microservices Architecture using Spring Boot, Spring Cloud, and Netflix OSS frameworks such as Hystrix, Eureka. 

## Table of Contents
* [Application Architecture](#application-architecture)
* [Spring Boot Overview] (#spring-boot-overview)
* [Spring Cloud Overview] (#spring-cloud-overview)
* [Spring Cloud Config Overview] (#spring-config-overview)
* [Spring Cloud Netflix Overview] (#spring-cloud-netflix-overview)

## <a name="application-architecture"></a>Application Architecture
Architecture consists of 6 business services, a centralized config server and service discovery server.Each component is built separately using their own build file. We use gradle as build system.

![Microservices Architecture](https://cloud.githubusercontent.com/assets/5256077/12713286/b81b20a4-c8f3-11e5-8ed5-b6d5170d50da.jpg)

### Architecture Components
* Core services responsible for handling information regarding accounts, payments and transactions. Account Core Service manages accounts, Payments Core Service manages payments for a given account and Transactions Core Service manages transactions for a given account.
    * [account core service] (accounts-core-service/README.md) - Account Core Service
    * [payments core service] (payments-core-service/README.md) - Payments Core Service for a given account
    * [transaction core service] (transactions-core-service/README.md) - Transaction Core Service for a given account

* Composite service, accounts-composite-service, that aggregates information from the two core services and composes a view of account information together with payment information for an account.

    * [account composite service] (accounts-composite-service/README.md) - Composite Service that aggregates the results of accounts and payment details for a given account

* API services, Accounts API Service retrieves account and payment information by invoking Accounts Composite Service and Transactions API Service retrieves transactions from Transactions Core Service.  
    * [account api service] (accounts-api-service/README.md) - API service for accounts
    * [transactions api service] (transactions-api-service/README.md) - API service for transactions

* [Config server] (sample-config-server/README.md) - Uses [Spring Cloud Config] (http://cloud.spring.io/spring-cloud-config/) for centralized management of external properties for applications across all environments. Spring Cloud is a new project in the spring.io family with a set of components that can be used to implement Microservices Architecture. To a large extent Spring Cloud 1.0 is based on components from Netflix OSS. Spring Cloud integrates the Netflix components in the Spring environment in a way using auto configuration and convention over configuration similar to how Spring Boot works.

* [Service discovery server] (discovery-server/README.md) - Uses [Netflix Eureka] (https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance) as service discovery server. Netflix Eureka allows microservices to register themselves at runtime as they appear in the system landscape.

* [Monitor Dashboard] (monitor-dashboard/README.md) - Uses Hystrix dashboard to provide a graphical overview of circuit breakers and Turbine, based on information in Eureka, to provide the dashboard with information from all circuit breakers in a system landscape. 

* Auth Server - Acts as the authetication and authorization service for the ecosystem of micro-services. 
[Work In Progress]

* Edge Server - Uses Zuul to act as API Gateway for routing and authenticating + authorizing API calls.
[Work In Progress]


## Using the Application

#### Running on local machine
* You can build the projects either by gradle or maven. 
    * Gradle: Run the gradlew file under each each project with command "gradlew clean build". This creates a jar file for each individual    project. Run the individual project jar by running the command: java -jar build/libs/< application_name >.jar command. 
    * Maven: Run maven at the parent project "microservices-demo", this will build all the individual projects. Run the  individual project jar by running the command: mvn spring-boot:run

* You can run the applications in the order listed below.
    * Config Server - This application should be run first as it holds properties for all applications 
    * Discovery Server - This application should be run second as all the services register themselves with discovery server
    * All other components (services (Core, Composite and API) and other supporting services)

* Please refer to the individual readme files on instructions of how to run the services. 

## <a name="spring-boot-overview"></a>Spring Boot Overview
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that can be "just run". 
Most Spring Boot applications need very little Spring configuration. Spring Boot embeds Tomcat, Jetty or Undertow directly and there is no need to deploy WAR files. You can use Spring Boot to create Java applications that can be started using java -jar or more traditional war deployments. It provides production-ready features such as metrics, health checks and externalized configuration.

## <a name="spring-cloud-overview"></a>Spring Cloud Overview
Spring Boot is app-centric. Spring Cloud builds on boot. The goal of Spring Cloud is to provide the Spring developers with an easily consumable set of tools to build distributed systems with common patterns (listed below) in distributed systems. It primarily does this by wrapping implementation stacks such as the Netflix OSS stack. These stacks are then consumed via the familiar tools of annotation-based configuration, Java configuration, and template-based programming.

* Distributed configuration: How to configure every instance of all of your services (standard boot config files checked into git or svn and distributed via config server).
* Service registration and discovery: how to locate a specific instance of a service (using Netflix Eureka)
* Client Side load balancing: intelligently choose an instance of a service to use (using Netflix Ribbon) via a smart algorithm such as: round robin or response time
* Plug into Ribbon via Spring Rest Template or Netflix Feign.
* Serve all assets and api's via a proxy that is plugged into service discovery and load balancing (Netflix Zuul).
* Stop cascading api failures with the Circuit Breaker pattern via Netflix Hystrix and visualize the health of all circuits with the Hystrix Dashboard.
* Send commands to all or some services via a lightweight message bus.
* Use oauth2 to protect resources

### <a name="spring-config-overview"></a>Spring Cloud Config Overview
Spring Cloud Config Server provides a centralized configuration service that is horizontally scalable. It uses as its data store a pluggable repository layer that currently supports local storage, Git, and Subversion. By leveraging a version control system as a configuration store, developers can easily version and audit configuration changes.

### <a name="spring-cloud-netflix-overview"></a>Spring Cloud Netflix Overview
Spring Cloud Netflix provides wrappers around several Netflix components: Eureka, Ribbon, Hystrix, and Zuul.

#### Eureka
[Eureka] (https://github.com/Netflix/eureka/wiki/Eureka-at-a-glance) is a resilient service registry implementation. A service registry is one mechanism for implementing the Service Discovery pattern. Spring Cloud Netflix enables the deployment of embedded Eureka servers by simply adding the spring-cloud-starter-eureka-server dependency to a Spring Boot application, then annotating that application’s configuration class with @EnableEurekaServer.

#### Hystrix
[Hystrix] (https://github.com/Netflix/Hystrix/wiki) provides an implementation of common fault-tolerance patterns for distributed systems such as circuit breakers and bulkheads. Circuit breakers are normally implemented as a state machine. Spring Cloud applications can leverage Hystrix by adding the spring-cloud-starter-hystrix dependency and annotating their configuration class with   @EnableCircuitBreaker. You can then add a circuit breaker to any Spring Bean method by annotating it with @HystrixCommand.

#### Hystrix Dashboard
[Hystrix Dashboard] (https://github.com/Netflix/Hystrix/wiki/Dashboard) In addition to providing the state machine behavior, Hystrix also emits a metrics stream from each circuit breaker providing important telemetry such as request metering, a response time histogram, and the number of successful, failed, and short-circuited requests.

#### Zuul
[Zuul] (https://github.com/Netflix/zuul/wiki) handles all incoming requests. It is used in combination with other Netflix components like Ribbon and Hystrix to provide a flexible and resilient routing tier for services.


