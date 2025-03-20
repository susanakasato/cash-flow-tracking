package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.util.Objects;

import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_cash_flow_detail")
public class CashFlowDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Column(length = 100, nullable = false)
	@NotNull(message = "Cash flow detail's title can't be null.", 
		groups = BasicDataValidation.class)
	private String title;
	
	@Column(nullable = true)
	private String description;
	
	@NotNull(message = "Cash flow detail's amount can't be null.",
			groups = BasicDataValidation.class)
	private Double amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (category_id) references tb_category on delete set null"))
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subcategory_id", foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (subcategory_id) references tb_subcategory on delete set null"))
	private Subcategory subcategory;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cash_flow_id")
	@JsonIgnore
	private CashFlow cashFlow;
	
	public CashFlowDetail () {}

	public CashFlowDetail(String id, String title, String description, Double amount, Category category,
			Subcategory subcategory) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.subcategory = subcategory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmout(Double amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public CashFlow getCashFlow() {
		return cashFlow;
	}

	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;
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
		CashFlowDetail other = (CashFlowDetail) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
