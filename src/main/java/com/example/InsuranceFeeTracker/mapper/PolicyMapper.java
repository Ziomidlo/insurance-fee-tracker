package com.example.InsuranceFeeTracker.mapper;

import com.example.InsuranceFeeTracker.dto.PolicyResponseDto;
import com.example.InsuranceFeeTracker.model.Policy;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    public PolicyResponseDto mapToDto(Policy policy) {
        boolean hasForm = policy.getSubmittedForms() != null && !policy.getSubmittedForms().isEmpty();
        boolean hasFee = policy.getFeeStatements() != null && !policy.getFeeStatements().isEmpty();

        return new PolicyResponseDto(
                policy.getFullPolicyNumber(),
                policy.getInsuranceCompany(),
                hasForm,
                hasFee
        );
    }
}
