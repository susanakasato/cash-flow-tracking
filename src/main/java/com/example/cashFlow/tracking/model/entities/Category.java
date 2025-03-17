package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_category")
public class Category extends CategorySuper implements Serializable {

	private static final long serialVersionUID = 1L;

	public Category() {}
	
	public Category(Integer id, String name) {
		super(id, name);
	}
}
