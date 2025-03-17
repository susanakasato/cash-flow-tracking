package com.example.cashFlow.tracking.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cashFlow.tracking.exceptions.DataValidationException;
import com.example.cashFlow.tracking.exceptions.ResourceNotFoundException;
import com.example.cashFlow.tracking.model.entities.CashFlowDetail;
import com.example.cashFlow.tracking.model.repositories.CashFlowDetailRepository;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.example.cashFlow.tracking.validations.Validation;

import jakarta.validation.groups.Default;

@Service
public class CashFlowDetailService {

	@Autowired
	private CashFlowDetailRepository repository;
	
	@Autowired
	private CashFlowService cashFlowService;
	
	public CashFlowDetail findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("No such cash flow detail of id #%s found in database.", id)));
	}
	
	public CashFlowDetail insert(CashFlowDetail cashFlowDetail, String cashFlowId) {
		cashFlowDetail.setCashFlow(cashFlowService.findById(cashFlowId));
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(cashFlowDetail, Default.class, BasicDataValidation.class);
		if (exceptionMessages.isEmpty()) return repository.save(cashFlowDetail);
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public CashFlowDetail update(String id, CashFlowDetail cashFlowDetailToUpdate) {
		Optional<String> exceptionMessages = Validation.getValidationExceptionMessage(cashFlowDetailToUpdate, Default.class, UpdateDataValidation.class);
		if (exceptionMessages.isEmpty()) {
			CashFlowDetail cashFlowDetail = this.findById(id);
			if (cashFlowDetailToUpdate.getTitle() != null) cashFlowDetail.setTitle(cashFlowDetailToUpdate.getTitle());
			if (cashFlowDetailToUpdate.getDescription() != null) cashFlowDetail.setDescription(cashFlowDetailToUpdate.getDescription());
			if (cashFlowDetailToUpdate.getAmount() != null) cashFlowDetail.setAmout(cashFlowDetailToUpdate.getAmount());
			if (cashFlowDetailToUpdate.getCategory() != null) cashFlowDetail.setCategory(cashFlowDetailToUpdate.getCategory());
			if (cashFlowDetailToUpdate.getSubcategory() != null) cashFlowDetail.setSubcategory(cashFlowDetailToUpdate.getSubcategory());
			return repository.save(cashFlowDetail);
		}
		throw new DataValidationException(exceptionMessages.get());
	}
	
	public void delete(String id) {
		repository.delete(this.findById(id));
	}
	
}
