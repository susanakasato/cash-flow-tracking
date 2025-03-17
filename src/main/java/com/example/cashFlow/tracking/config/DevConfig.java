package com.example.cashFlow.tracking.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.cashFlow.tracking.model.entities.CashFlow;
import com.example.cashFlow.tracking.model.entities.Category;
import com.example.cashFlow.tracking.model.entities.User;
import com.example.cashFlow.tracking.model.entities.enums.CashFlowOperation;
import com.example.cashFlow.tracking.model.services.CashFlowService;
import com.example.cashFlow.tracking.model.services.CategoryService;
import com.example.cashFlow.tracking.model.services.UserService;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CashFlowService cashFlowService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public void run(String... args) throws Exception {

		User user1 = new User("a3afe7fa-8e77-4112-8ffe-475db7bbe63e", "David Williams", "david123", "david@gmail.com", "12341234");
		User user2 = new User(null, "James Miller", "jamesmiller", "james@gmail.com", "james123");
		User user3 = new User(null, "Elizabeth Johnson", "elizabethj", "elizabeth@gmail.com", "elijohnson");
		userService.insert(user1);
		userService.insert(user2);
		userService.insert(user3);
		
		CashFlow cashFlow1 = new CashFlow(null, LocalDate.now(), CashFlowOperation.IN);
		CashFlow cashFlow2 = new CashFlow(null, LocalDate.now(), CashFlowOperation.OUT);
		cashFlowService.insert(user1.getId(), cashFlow1);
		cashFlowService.insert(user1.getId(), cashFlow2);
		
		Category category1 = new Category(null, "Groceries");
		Category category2 = new Category(null, "Health");
		Category category3 = new Category(null, "Car");
		Category category4 = new Category(null, "Restaurant");
		categoryService.insert(category1, null);
		categoryService.insert(category2, null);
		categoryService.insert(category3, user1.getId());
		categoryService.insert(category4, user1.getId());

	}

}
