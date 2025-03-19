package com.example.cashFlow.tracking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.User;
import com.example.cashFlow.tracking.model.services.UserService;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = userService.findByUsername(username);
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), null);
			System.out.println("User details:");
			System.out.println(userDetails.getPassword());
			return userDetails;
		} catch (ResourceNotFoundException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

}
