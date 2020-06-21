package com.eureka.auth.util;

import java.sql.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.eureka.common.security.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	public String generateJwtToken(Authentication auth) {
		Long now = System.currentTimeMillis();
		return Jwts.builder()
			.setSubject(auth.getName())	
			// Convert to list of strings. 
			// This is important because it affects the way we get them back in the Gateway.
			.claim("authorities", auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + new JwtConfig().getExpiration() * 1000))  // in milliseconds
			.signWith(SignatureAlgorithm.HS512, new JwtConfig().getSecret().getBytes())
			.compact();
	}
}
