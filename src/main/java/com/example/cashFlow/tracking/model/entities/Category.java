package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "tb_category")
public class Category extends CategorySuper implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	@Null(message = "Can't change the owner user of category or subcategory.",
			groups = UpdateDataValidation.class)
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	public Category() {}
	
	public Category(Integer id, String name) {
		super(id, name);
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Subcategory> getSubcategories() {
		return subcategories;
	}
	
}
