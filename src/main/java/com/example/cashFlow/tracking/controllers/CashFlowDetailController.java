package com.example.cashFlow.tracking.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cashFlow.tracking.model.entities.CashFlowDetail;
import com.example.cashFlow.tracking.model.services.CashFlowDetailService;

@RestController
@RequestMapping("cash-flow-details")
public class CashFlowDetailController {
	
	@Autowired
	private CashFlowDetailService service;

	@PostMapping("/cash-flow/{cashFlowId}")
	public ResponseEntity<CashFlowDetail> insert(@PathVariable String cashFlowId, @RequestBody CashFlowDetail cashFlowDetail) {
		cashFlowDetail = service.insert(cashFlowDetail, cashFlowId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cashFlowDetail.getId()).toUri();
		return ResponseEntity.created(uri).body(cashFlowDetail);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CashFlowDetail> update(@PathVariable String id, @RequestBody CashFlowDetail cashFlowDetail) {
		cashFlowDetail = service.update(id, cashFlowDetail);
		return ResponseEntity.ok(cashFlowDetail);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
