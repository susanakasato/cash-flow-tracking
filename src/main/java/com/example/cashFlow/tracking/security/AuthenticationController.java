package com.example.cashFlow.tracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("auth")
	public String authenticate(Authentication authentication) {
		System.out.println("PASSO 1");
		return authenticationService.authenticate(authentication);
	}
}
