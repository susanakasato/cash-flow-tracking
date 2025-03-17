package com.example.cashFlow.tracking.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cashFlow.tracking.model.entities.CashFlowDetail;

public interface CashFlowDetailRepository extends JpaRepository<CashFlowDetail, String> {

}
