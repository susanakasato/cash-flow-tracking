package com.example.cashFlow.tracking.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.Category;
import com.example.cashFlow.tracking.model.entities.User;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByUser(User user);
}
