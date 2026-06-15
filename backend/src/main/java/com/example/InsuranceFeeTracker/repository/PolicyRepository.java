package com.example.InsuranceFeeTracker.repository;

import com.example.InsuranceFeeTracker.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    Optional<Policy> findByFullPolicyNumber(String fullPolicyNumber);

}
