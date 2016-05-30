## Overview
Hystrix dashboard is used to provide a graphical overview of circuit breakers. 

Run this app as a normal Spring Boot app and then go to the home page in a browser (http://localhost:7979/hystrix). If you run from this project it will be on port 7979 (per the application.yml). On the home page is a form where you can enter the URL for an event stream to monitor, for example:

* the accounts api service running locally: http://localhost:8082/hystrix.stream and 
* the transactions api service running locally: http://localhost:8083/hystrix.stream

Sample screenshot for Hystrix Dashboard for Accounts API Service:

![Hystrix Dashboard for Accounts API Service](https://cloud.githubusercontent.com/assets/5256077/12741287/f3751954-c9a0-11e5-8595-f9e0f83e06ac.jpg)

Sample screenshot for Hystrix Dashboard for Transactions API Service:
![Hystrix Dashboard for Transactions API Service](https://cloud.githubusercontent.com/assets/5256077/12741300/0f5f3334-c9a1-11e5-8f17-aae1c592b631.jpg)
