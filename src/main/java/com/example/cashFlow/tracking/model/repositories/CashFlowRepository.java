package com.example.cashFlow.tracking.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.CashFlow;
import com.example.cashFlow.tracking.model.entities.User;

public interface CashFlowRepository extends JpaRepository<CashFlow, String>{

	List<CashFlow> findByUser(User user);
}
