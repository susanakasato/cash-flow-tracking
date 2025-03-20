package com.example.cashFlow.tracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.services.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return new UserDetailsImpl(userService.findByUsername(username));
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
