package com.example.InsuranceFeeTracker.mapper;

import com.example.InsuranceFeeTracker.dto.PolicyDetailsDTO;
import com.example.InsuranceFeeTracker.dto.PolicyResponseDTO;
import com.example.InsuranceFeeTracker.model.Policy;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {

    public PolicyResponseDTO mapToDto(Policy policy) {
        boolean hasForm = policy.getSubmittedForms() != null && !policy.getSubmittedForms().isEmpty();
        boolean hasFee = policy.getFeeStatements() != null && !policy.getFeeStatements().isEmpty();

        return new PolicyResponseDTO(
                policy.getId(),
                policy.getPolicySeries(),
                policy.getPolicyNumber(),
                policy.getFullPolicyNumber(),
                policy.getInsuranceCompany(),
                hasForm,
                hasFee
        );
    }

    public PolicyDetailsDTO mapToPolicyDetails(Policy policy) {
        return new PolicyDetailsDTO(
                policy.getId(),
                policy.getPolicySeries(),
                policy.getPolicyNumber(),
                policy.getFullPolicyNumber(),
                policy.getInsuranceCompany(),
                policy.getSubmittedForms(),
                policy.getFeeStatements()
        );
    }
}
