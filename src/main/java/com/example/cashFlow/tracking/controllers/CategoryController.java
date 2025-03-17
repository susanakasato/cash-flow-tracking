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

import com.example.cashFlow.tracking.model.entities.Category;
import com.example.cashFlow.tracking.model.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category category = service.findById(id);
		return ResponseEntity.ok(category);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Category>> findByUser(@PathVariable String userId){
		List<Category> categories = service.findByUser(userId);
		return ResponseEntity.ok(categories);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<Category> insert(@PathVariable String userId, @RequestBody Category category) {
		category = service.insert(category, userId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
		category = service.update(id, category);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
