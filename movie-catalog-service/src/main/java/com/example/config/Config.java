package com.example.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}
	
	/*
	 * @Bean(name = "hystrixRegistrationBean") public ServletRegistrationBean
	 * servletRegistrationBean() { ServletRegistrationBean registration = new
	 * ServletRegistrationBean( new HystrixMetricsStreamServlet(),
	 * "/hystrix.stream"); registration.setName("hystrixServlet");
	 * registration.setLoadOnStartup(1); return registration; }
	 */

}
