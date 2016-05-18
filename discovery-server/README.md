

## Overview
This application provides the Eureka Server that provides service discovery and enables all Eureka clients to discover each other.

When a client registers with Eureka, it provides meta-data about itself such as host and port, health indicator URL, home page etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance is normally removed from the registry.

## Pre-requisites

### Projects that need to be started before

* [config server] (https://github.com/mrudul03/microservices-demo/blob/master/sample-config-server/README.md) - For pulling the configuration information

## Running the application

* Build the application by running the command gradlew clean build gradle command at the "discovery-server" project root folder on the terminal.
* Run the application as jar file by running the command: java -jar build/libs/discovery-server-1.0.0.jar at the terminal.

External Configuration
The project derives it's external configuration from the [config server] (https://github.com/mrudul03/microservices-demo/blob/master/sample-config-server/README.md) service. We have defined the spring.cloud.config.uri in the bootstrap.yml file and that tells the application where to pick up the external confiurations. In our case, the URL points to the running [config server] (https://github.com/mrudul03/microservices-demo/blob/master/sample-config-server/README.md). You also need to have the spring-cloud-config-client dependency in the classpath so that the application can comsume the config server.
