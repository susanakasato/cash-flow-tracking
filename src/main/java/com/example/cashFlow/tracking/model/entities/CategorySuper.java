package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.util.Objects;

import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@MappedSuperclass
public abstract class CategorySuper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(length = 30, nullable = false)
	@NotNull(message = "'Name' of category or subcategory can't be null.", 
			groups = BasicDataValidation.class)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	@Null(message = "Can't change the owner user of category or subcategory.",
			groups = UpdateDataValidation.class)
	@JsonIgnore
	private User user;
	
	public CategorySuper() {}

	public CategorySuper(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategorySuper other = (CategorySuper) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
