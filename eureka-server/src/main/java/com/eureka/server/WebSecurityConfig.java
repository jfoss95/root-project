package com.eureka.server;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity 	// Enable security config. This annotation denotes config for spring security.
*/
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {
	/*
	@Override
  	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/user").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic();
	}

	/*
	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	    return http
	    		.csrf().disable()
	            .authorizeExchange()
	            .pathMatchers(HttpMethod.GET, "/user/**").permitAll()
	            //.pathMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
	            .pathMatchers(HttpMethod.POST, "/user/**").permitAll()
	            .pathMatchers("/user/**").authenticated()
	            .anyExchange().permitAll()
	            .and()
	            .httpBasic()
	            .and()
	            .build();
	}
	*/
}