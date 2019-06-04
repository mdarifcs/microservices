package com.example.resources;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest")
public class HystrixResource {
	
	public static final Logger LOG = LoggerFactory.getLogger(HystrixResource.class);

	@HystrixCommand(fallbackMethod="fallBackHello", commandKey="hello", groupKey="hello")
	@GetMapping("/hello")
	public String hello() {
		if (RandomUtils.nextBoolean()) {
			throw new RuntimeException("Failed");
		}
		return "Hello world";
	}
	
	public String fallBackHello(Throwable hystrixCommand) {
		LOG.error("fallbackHello ",hystrixCommand);
		return "fallback hello initiated";
	}
	
}
