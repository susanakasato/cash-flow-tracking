package com.example.cashFlow.tracking.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.DataValidationException;
import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.CashFlow;
import com.example.cashFlow.tracking.model.entities.User;
import com.example.cashFlow.tracking.model.entities.enums.CashFlowOperation;
import com.example.cashFlow.tracking.model.repositories.CashFlowRepository;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.example.cashFlow.tracking.validations.Validation;

import jakarta.validation.groups.Default;

@Service
public class CashFlowService {

	@Autowired
	private CashFlowRepository repository;
	
	@Autowired
	private UserService userService;
	
	public CashFlow findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Cash flow of id #%s not found in database.", id)));
	}
	
	public List<CashFlow> findByUser(User user) {
		return repository.findByUser(user);
	}
	
	public CashFlow insert(String userId, CashFlow cashflow) {
		cashflow.setUser(userService.findById(userId));
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(cashflow, Default.class, BasicDataValidation.class);
		if (exceptionMessages.isEmpty()) return repository.save(cashflow);
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public CashFlow update(String id, CashFlow cashFlowToUpdate) {
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(cashFlowToUpdate, Default.class, UpdateDataValidation.class);
		if (exceptionMessages.isEmpty()) {
			CashFlow cashFlow = this.findById(id);
			if (cashFlowToUpdate.getDate() != null) cashFlow.setDate(cashFlowToUpdate.getDate());
			if (cashFlowToUpdate.getOperation() != null) cashFlow.setOperation(CashFlowOperation.valueOf(cashFlowToUpdate.getOperation()));
			return repository.save(cashFlow);
		}
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public void delete(String id) {
		repository.delete(this.findById(id));
	}

}
