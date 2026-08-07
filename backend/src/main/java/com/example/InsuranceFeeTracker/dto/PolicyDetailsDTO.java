package com.example.InsuranceFeeTracker.dto;

import com.example.InsuranceFeeTracker.model.FeeStatement;
import com.example.InsuranceFeeTracker.model.SubmittedForm;

import java.util.List;

public record PolicyDetailsDTO(
    Long id,
    String policySeries,
    String policyNumber,
    String insuranceCompany,
    List<SubmittedForm> submittedFormList,
    List<FeeStatement> feeStatementList
) {}