package com.example.config.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest")
@RestController
public class MessageResource {

	@Value("${message: default Hello}")
	private String message;
	
	@GetMapping("/message")
	public String message() {
		return message;
	}
}
