package com.eureka.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;

@EnableClusterConfiguration(useHttp = true, requireHttps=false)
@CacheServerApplication(name = "server", locators = "localhost[10334]")
@SpringBootApplication
public class DataServerApp {
	public static void main(String[] args) {
		SpringApplication.run(DataServerApp.class, args);
	}
}