package com.example.cashFlow.tracking.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.cashFlow.tracking.model.entities.CashFlow;
import com.example.cashFlow.tracking.model.entities.User;
import com.example.cashFlow.tracking.model.entities.enums.CashFlowOperation;
import com.example.cashFlow.tracking.model.services.CashFlowService;
import com.example.cashFlow.tracking.model.services.UserService;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CashFlowService cashFlowService;
	
	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "David Williams", "david123", "david@gmail.com", "12341234");
		User user2 = new User(null, "James Miller", "jamesmiller", "james@gmail.com", "james123");
		User user3 = new User(null, "Elizabeth Johnson", "elizabethj", "elizabeth@gmail.com", "elijohnson");
		userService.insert(user1);
		userService.insert(user2);
		userService.insert(user3);
		
		CashFlow cashFlow1 = new CashFlow(null, LocalDate.now(), CashFlowOperation.IN);
		CashFlow cashFlow2 = new CashFlow(null, LocalDate.now(), CashFlowOperation.OUT);
		cashFlowService.insert(user1.getId(), cashFlow1);
		cashFlowService.insert(user1.getId(), cashFlow2);
	}

}
