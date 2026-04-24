package com.example.InsuranceFeeTracker.dto;

public record PolicyResponseDto(
        String fullPolicyNumber,
        String insuranceCompany,
        boolean hasSubmittedForm,
        boolean hasFeeStatement
) {}
