package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author mohammad.arif
 * discovery service in spring cloud has many implementations (eureka, consul, zookeeper, etc.), @EnableDiscoveryClient is based on
 * spring-cloud-commons, @EnableEurekaClient is based on spring-cloud-netflix.
 * 
 * In terms of its practicality and simplicity, if the registered center is eureka, then @EnableEurekaClient is recommended. 
 * If it is another registration center, @EnableDiscoveryClient is recommended
 * 
 * Use @EnableHystrix to implement the circuit breaker pattern specifically with Hystrix on the classpath.
 * Use @EnableCircuitBreaker to implement the circuit breaker pattern with Hystrix or an alternative circuit breaker implementation.
 * @EnableHystrix internally use @EnableCircuitBreaker design pattern. 
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
public class MovieCatalogServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
