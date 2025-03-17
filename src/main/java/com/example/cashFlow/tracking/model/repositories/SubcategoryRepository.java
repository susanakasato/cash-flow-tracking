package com.example.cashFlow.tracking.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.Subcategory;

public interface  SubcategoryRepository extends JpaRepository<Subcategory, Integer>{

}
