package com.eureka.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eureka.auth.eo.DataServiceEO;
import com.eureka.common.exception.ServiceException;
import com.eureka.common.exception.UserNotFoundException;
import com.eureka.auth.vo.UserVO;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	@Autowired
	private DataServiceEO dataServiceEO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			UserVO user = dataServiceEO.getUserDetails(username);
			return UserDetailsImpl.build(user);
		} catch(ServiceException | UserNotFoundException e){
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
	}
}