package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_subcategory")
public class Subcategory extends CategorySuper implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnore
	private Category category;
	
	@OneToMany(mappedBy = "subcategory")
	@JsonIgnore
	private List<CashFlowDetail> cashFlowDetails = new ArrayList<CashFlowDetail>();
	
	public Subcategory() {}
	
	public Subcategory(Integer id, String name) {
		super(id, name);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
