package com.example.resources;



import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest")
public class HystrixResource {

	@HystrixCommand(fallbackMethod="fallBackHello", commandKey="hello", groupKey="hello")
	@GetMapping("/hello")
	public String hello() {
		
		if (RandomUtils.nextBoolean()) {
			throw new RuntimeException("Failed");
		}
		return "Hello world";
	}
	
	public String fallBackHello() {
		return "fallback hello initiated";
	}
	
}
