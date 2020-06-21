package com.eureka.auth.bo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eureka.auth.eo.DataServiceEO;
import com.eureka.auth.exception.ServiceException;
import com.eureka.auth.security.UserDetailsImpl;
import com.eureka.auth.util.JwtUtil;
import com.eureka.auth.vo.RoleName;
import com.eureka.auth.vo.SignInResponseVO;
import com.eureka.auth.vo.SignUpRequestVO;
import com.eureka.auth.vo.UserCredentialsVO;
import com.eureka.auth.vo.UserVO;

public class AuthServiceBO {
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceBO.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	DataServiceEO dataServiceEO;
	
	public SignInResponseVO authenticateUser(UserCredentialsVO userCredentialsVO) {
		logger.info("Begginning authentication of user");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userCredentialsVO.getUsername(), userCredentialsVO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new SignInResponseVO(jwt, roles, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
	}
	
	public void registerUser(SignUpRequestVO signUpRequestVO) throws ServiceException {
		UserVO userVO = new UserVO(signUpRequestVO.getUsername(), encoder.encode(signUpRequestVO.getPassword()), signUpRequestVO.getEmail());
		Set<RoleName> roles = new HashSet<>();
		roles.add(RoleName.ROLE_USER);
		userVO.setRoles(roles);
		dataServiceEO.addUser(userVO);
	}
}
