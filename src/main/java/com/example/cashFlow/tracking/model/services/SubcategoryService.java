package com.example.cashFlow.tracking.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.DataValidationException;
import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.Subcategory;
import com.example.cashFlow.tracking.model.repositories.SubcategoryRepository;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.example.cashFlow.tracking.validations.Validation;

import jakarta.validation.groups.Default;

@Service
public class SubcategoryService {

	@Autowired
	private SubcategoryRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	public Subcategory findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No such subcategory of id #%d found in database.", id)));
	}
	
	public Subcategory insert(Subcategory subcategory, Integer categoryId) {
		subcategory.setCategory(categoryService.findById(categoryId));
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(subcategory, Default.class, BasicDataValidation.class);
		if (exceptionMessages.isEmpty()) return repository.save(subcategory);
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public Subcategory update(Integer id, Subcategory subcategoryToUpdate, Integer changedCategoryId) {
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(changedCategoryId, Default.class, UpdateDataValidation.class);
		if (exceptionMessages.isEmpty()) {
			Subcategory subcategory = this.findById(id);
			if (subcategoryToUpdate.getName() != null) subcategory.setName(subcategoryToUpdate.getName());
			if (changedCategoryId != null) subcategory.setCategory(categoryService.findById(changedCategoryId));
			return repository.save(subcategory);
		}
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public void delete(Integer id) {
		repository.delete(this.findById(id));
	}
		
}
