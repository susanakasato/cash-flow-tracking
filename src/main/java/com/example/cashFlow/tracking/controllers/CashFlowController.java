package com.example.cashFlow.tracking.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.cashFlow.tracking.model.entities.CashFlow;
import com.example.cashFlow.tracking.model.services.CashFlowService;

@RestController
@RequestMapping("cash-flows")
public class CashFlowController {

	@Autowired
	private CashFlowService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<CashFlow> findById(@PathVariable String id) {
		CashFlow cashFlow = service.findById(id);
		return ResponseEntity.ok(cashFlow);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<CashFlow>> findByUser(@PathVariable String userId) {
		List<CashFlow> cashFlows = service.findByUser(userId);
		return ResponseEntity.ok(cashFlows);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<CashFlow> insert(@PathVariable String userId, @RequestBody CashFlow cashFlow) {
		cashFlow = service.insert(userId, cashFlow);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cashFlow.getId()).toUri();
		return ResponseEntity.created(uri).body(cashFlow);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CashFlow> update(@PathVariable String id, @RequestBody CashFlow cashFlow) {
		System.out.println(cashFlow);
		cashFlow = service.update(id, cashFlow);
		return ResponseEntity.ok(cashFlow);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
