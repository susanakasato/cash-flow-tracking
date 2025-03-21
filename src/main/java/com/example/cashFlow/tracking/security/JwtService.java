package com.example.cashFlow.tracking.security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	@Autowired
	private JwtEncoder jwtEncoder;
	
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		Long expiration = 3600L;
		var claims = JwtClaimsSet.builder()
				.issuer("tracking")
				.issuedAt(now)
				.expiresAt(now.plusSeconds(expiration))
				.subject(authentication.getName())
				.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
