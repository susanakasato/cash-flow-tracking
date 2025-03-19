package com.example.cashFlow.tracking.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User> findByUsername(String username);
}
