package com.example.cashFlow.tracking.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.DataValidationException;
import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.Category;
import com.example.cashFlow.tracking.model.repositories.CategoryRepository;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.example.cashFlow.tracking.validations.Validation;

import jakarta.validation.groups.Default;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private UserService userService;
	
	public Category findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No such category of id #%d found in database.", id)));
	}
	
	public List<Category> findByUser(String userId) {
		return repository.findByUser(userService.findById(userId));
	}
	
	public Category insert(Category category, String userId) {
		if (userId != null) category.setUser(userService.findById(userId));
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(category, Default.class, BasicDataValidation.class);
		if (exceptionMessages.isEmpty()) return repository.save(category);
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public Category update(Integer id, Category categoryToUpdate) {
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(categoryToUpdate, Default.class, UpdateDataValidation.class);
		if (exceptionMessages.isEmpty()) {
			Category category = this.findById(id);
			if (categoryToUpdate.getName() != null) category.setName(categoryToUpdate.getName());
			return repository.save(category);
		}
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public void delete(Integer id) {
		repository.delete(this.findById(id));
	}
}
