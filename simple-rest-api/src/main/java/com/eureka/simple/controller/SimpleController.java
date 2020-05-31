package com.eureka.simple.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.simple.vo.GreetingVO;

@RestController
@RequestMapping("/")
public class SimpleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleController.class);
	
	@Autowired
	private Environment environment;
	
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Gallery Service running at port: " + environment.getProperty("local.server.port");
	}

	@GetMapping("/user")
	public GreetingVO homeUser(@RequestParam(value = "name", defaultValue = "unknown") String name) {
		StringBuilder userMessage = new StringBuilder("Hello USER ");
		userMessage.append(name);
		return new GreetingVO(counter.incrementAndGet(), userMessage.toString());
	}
	
	// -------- Admin Area --------
	@GetMapping("/admin")
	public GreetingVO homeAdmin(@RequestParam(value = "name", defaultValue = "unknown")String name) {
		StringBuilder adminMessage = new StringBuilder("Hello ADMIN ");
		adminMessage.append(name);
		return new GreetingVO(counter.incrementAndGet(), adminMessage.toString());
	}
}