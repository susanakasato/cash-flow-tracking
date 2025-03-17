package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.example.cashFlow.tracking.model.entities.enums.CashFlowOperation;
import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_cash_flow")
public class CashFlow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(length = 40)
	private String id;
	
	@NotNull(message = "Cash flow's 'date' can't be null.",
			groups = BasicDataValidation.class)
	@Column(nullable = false)
	private LocalDate date;
	
	@NotNull(message = "Cash flow's 'operation' can't be null.",
			groups = BasicDataValidation.class)
	@Column(nullable = false)
	private String operation;
	
	@ManyToOne()
	@JsonIgnore
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public CashFlow() {}

	public CashFlow(String id, LocalDate date, CashFlowOperation operation) {
		this.id = id;
		this.date = date;
		this.operation = operation.getCode();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(CashFlowOperation operation) {
		this.operation = operation.getCode();
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
		CashFlow other = (CashFlow) obj;
		return Objects.equals(id, other.id);
	}


}
