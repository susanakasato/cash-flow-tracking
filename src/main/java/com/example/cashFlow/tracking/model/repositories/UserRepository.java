package com.example.cashFlow.tracking.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
