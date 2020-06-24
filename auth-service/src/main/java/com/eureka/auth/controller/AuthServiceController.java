package com.eureka.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.bo.AuthServiceBO;
import com.eureka.auth.util.JwtUtil;
import com.eureka.auth.vo.MessageVO;
import com.eureka.auth.vo.SignInResponseVO;
import com.eureka.auth.vo.SignUpRequestVO;
import com.eureka.auth.vo.UserCredentialsVO;
import com.eureka.common.exception.EmailExistsException;
import com.eureka.common.exception.IncorrectFormatException;
import com.eureka.common.exception.ServiceException;
import com.eureka.common.exception.UsernameExistsException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthServiceController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AuthServiceBO authServiceBO;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/signin")
	public ResponseEntity<SignInResponseVO> authenticateUser(@Valid @RequestBody UserCredentialsVO userCredentialsVO) {
		SignInResponseVO signInResponseVO = authServiceBO.authenticateUser(userCredentialsVO);
		return ResponseEntity.ok(signInResponseVO);
	}

	@PostMapping("/signup")
	public ResponseEntity<MessageVO> registerUser(@Valid @RequestBody SignUpRequestVO signInRequestVO) 
			throws ServiceException, UsernameExistsException, EmailExistsException, IncorrectFormatException {
		authServiceBO.registerUser(signInRequestVO);
		return ResponseEntity.ok(new MessageVO("User registered successfully!"));
	}
}