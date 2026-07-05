package com.example.InsuranceFeeTracker.dto;

public record PolicyResponseDto(
        Long id,
        String policySeries,
        String policyNumber,
        String fullPolicyNumber,
        String insuranceCompany,
        boolean hasSubmittedForm,
        boolean hasFeeStatement
) {}
