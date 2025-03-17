package com.example.cashFlow.tracking.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.cashFlow.tracking.validations.BasicDataValidation;
import com.example.cashFlow.tracking.validations.UpdateDataValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email" }))
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(length = 40)
	private String id;

	@Column(nullable = false, 
			length = 100)
	@NotNull(message = "User's 'name' property can't be null.", 
		groups = BasicDataValidation.class)
	@Size(min = 5, 
		max = 100, 
		message = "User's 'name' property size must be between 10 and 100 characters.")
	private String name;

	@Column(nullable = false, length = 30)
	@NotNull(message = "User's 'username' property can't be null.",
			groups = BasicDataValidation.class)
	@Size(min = 5, 
		max = 30, 
		message = "User's 'username' property size must be between 5 and 30 characters.")
	private String username;

	@Column(nullable = false)
	@NotNull(message = "User's 'email' property can't be null.", 
			groups = BasicDataValidation.class)
	@Null(message = "User's 'email' can't be changed.", 
			groups = UpdateDataValidation.class)	
	@Email(message = "User's 'email' must have email address format.")
	private String email;

	@Column(nullable = false, length = 20)
	@NotNull(message = "User's 'password' property can't be null.",
			groups = BasicDataValidation.class)
	@Size(min = 8, 
		max = 20, 
		message = "User's 'password' property size must be between 8 and 20 characters.")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<CashFlow> cashFlows = new ArrayList<CashFlow>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Category> categories = new ArrayList<Category>();

	public User() {}
	
	public User(String id, String name, String username, String email, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<CashFlow> getCashFlows() {
		return cashFlows;
	}
	
	public List<Category> getCategories() {
		return categories;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
