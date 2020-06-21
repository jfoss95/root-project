package com.eureka.auth.security;

import java.util.List;
import java.util.Set;

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
import com.eureka.auth.exception.ServiceException;
import com.eureka.auth.vo.RoleName;
import com.eureka.auth.vo.UserVO;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DataServiceEO dataServiceEO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try{
			UserVO user = dataServiceEO.getUserDetails(username);
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                	.commaSeparatedStringToAuthorityList(formatRolesToString(user.getRoles()));
			return new User(user.getUsername(), encoder.encode(user.getPassword()), grantedAuthorities);
		} catch(ServiceException e){
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
	}
	
	private String formatRolesToString(Set<RoleName> roles) {
		StringBuilder rolesString = new StringBuilder();
		for(RoleName role: roles) {
			rolesString.append(role);
			rolesString.append(",");
		}
		return rolesString.toString();
	}
}