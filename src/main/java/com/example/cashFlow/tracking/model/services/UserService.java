package com.example.cashFlow.tracking.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.DataValidationException;
import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.User;
import com.example.cashFlow.tracking.model.repositories.UserRepository;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.example.cashFlow.tracking.validations.Validation;

import jakarta.validation.groups.Default;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User of id #%s not found in database.", id)));
	}
	
	public User insert(User user) {
		Optional<String> exceptionMessage = Validation.getValidationExceptionMessage(user, Default.class, BasicDataValidation.class);
		if (exceptionMessage.isEmpty()) return repository.save(user);
		throw new DataValidationException(exceptionMessage.get());
	}
	
	public User update(String id, User userToUpdate) {
		Optional<String> exceptionMessage = Validation.getValidationExceptionMessage(userToUpdate, Default.class, UpdateDataValidation.class);
		if (exceptionMessage.isEmpty()) {
			User user = repository.findById(id).get();
			if (userToUpdate.getName() != null) user.setName(userToUpdate.getName());
			if (userToUpdate.getUsername() != null) user.setUsername(userToUpdate.getUsername());
			if (userToUpdate.getPassword() != null) user.setPassword(userToUpdate.getPassword());
			return repository.save(user);
		}
		throw new DataValidationException(exceptionMessage.get());
	}
	
	public void delete(String id) {
		repository.delete(this.findById(id));
	}
}
