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

import com.example.cashFlow.tracking.model.entities.Subcategory;
import com.example.cashFlow.tracking.model.services.SubcategoryService;

@RestController
@RequestMapping("subcategories")
public class SubcategoryController {

	@Autowired
	private SubcategoryService service;
	
	@PostMapping("/category/{categoryId}")
	public ResponseEntity<Subcategory> insert(@PathVariable Integer categoryId, @RequestBody Subcategory subcategory) {
		subcategory = service.insert(subcategory, categoryId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subcategory.getId()).toUri();
		return ResponseEntity.created(uri).body(subcategory);
	}
	
	@PutMapping("/{id}/category/{categoryId}")
	public ResponseEntity<Subcategory> update(@PathVariable Integer id, @PathVariable Integer categoryId, @RequestBody Subcategory subcategory) {
		subcategory = service.update(id, subcategory, categoryId);
		return ResponseEntity.ok(subcategory);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Subcategory> update(@PathVariable Integer id, @RequestBody Subcategory subcategory) {
		subcategory = service.update(id, subcategory, null);
		return ResponseEntity.ok(subcategory);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
