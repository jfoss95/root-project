package com.eureka.auth.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.auth.eo.DataServiceEO;
import com.eureka.data.vo.UserCredentialsVO;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DataServiceEO dataServiceEO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*
		final List<AppUser> users = Arrays.asList(
			new AppUser(1, "jack", encoder.encode("12345"), "USER"),
			new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
		);
		*/
		UserCredentialsVO user = dataServiceEO.getUserDetails(username);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                	.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
		return new User(user.getUsername(), encoder.encode(user.getPassword()), grantedAuthorities);
		
		// If user not found. Throw this exception (try catch around EO call)
		//throw new UsernameNotFoundException("Username: " + username + " not found");
	}
}