package com.example.InsuranceFeeTracker.repository;

import com.example.InsuranceFeeTracker.model.SubmittedForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedFormRepository extends JpaRepository<SubmittedForm, Long> {

}
