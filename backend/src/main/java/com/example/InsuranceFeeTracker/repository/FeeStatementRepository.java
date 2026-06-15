package com.example.InsuranceFeeTracker.repository;

import com.example.InsuranceFeeTracker.model.FeeStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeStatementRepository extends JpaRepository<FeeStatement, Long> {

}
