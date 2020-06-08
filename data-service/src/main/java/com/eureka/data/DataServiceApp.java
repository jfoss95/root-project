package com.eureka.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableIndexing;
import org.springframework.data.gemfire.config.annotation.EnablePdx;
import org.springframework.data.gemfire.config.annotation.EnableSecurity;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import com.eureka.data.controller.DataServiceController;
import com.eureka.data.model.UserInfo;
import com.eureka.data.repo.UserCredentialsRepository;

@EnableGemfireRepositories(basePackageClasses = UserCredentialsRepository.class)
@EnableEntityDefinedRegions(basePackageClasses = UserInfo.class)
@EnableIndexing
@ComponentScan(basePackageClasses = DataServiceController.class)
@EnablePdx
@EnableClusterConfiguration(useHttp = true, requireHttps=false)
@ClientCacheApplication(subscriptionEnabled = true)
//@EnableContinuousQueries
//@EnableSecurity
@SpringBootApplication//(scanBasePackages="com.eureka.data")
@ComponentScan("com.eureka.data")
public class DataServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(DataServiceApp.class, args);
	}
}