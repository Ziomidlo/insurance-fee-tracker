package com.example.InsuranceFeeTracker.service;

import com.example.InsuranceFeeTracker.model.FeeStatement;
import com.example.InsuranceFeeTracker.model.Policy;
import com.example.InsuranceFeeTracker.model.SubmittedForm;
import com.example.InsuranceFeeTracker.repository.FeeStatementRepository;
import com.example.InsuranceFeeTracker.repository.PolicyRepository;
import com.example.InsuranceFeeTracker.repository.SubmittedFormRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyProcessingService {

    private final PolicyRepository policyRepository;
    private final SubmittedFormRepository submittedFormRepository;
    private final FeeStatementRepository feeStatementRepository;

    @Transactional
    public void processSubmittedForm(String extractedPolicySeries, String extractedPolicyNumber, String insuranceCompany ,SubmittedForm extractedForm){
        String fullPolicyNumber = extractedPolicySeries + extractedPolicyNumber;

        Policy policy = policyRepository.findByFullPolicyNumber(fullPolicyNumber)
                .orElseGet(() -> {
                    Policy newPolicy = Policy.builder()
                            .policySeries(extractedPolicySeries)
                            .policyNumber(extractedPolicyNumber)
                            .fullPolicyNumber(fullPolicyNumber)
                            .insuranceCompany(insuranceCompany)
                            .build();
                    return policyRepository.save(newPolicy);
                });

        extractedForm.setPolicy(policy);
        submittedFormRepository.save(extractedForm);

    }

    @Transactional
    public void processFeeStatement(String extractPolicySeries, String extractPolicyNumber, String insuranceCompany, FeeStatement extractedFeeStatement) {

        String fullPolicyNumber = extractPolicySeries + extractPolicyNumber;
        Policy policy = policyRepository.findByFullPolicyNumber(fullPolicyNumber)
                .orElseGet(() -> {
                    Policy newPolicy = Policy.builder()
                            .policySeries(extractPolicySeries)
                            .policyNumber(extractPolicyNumber)
                            .fullPolicyNumber(fullPolicyNumber)
                            .insuranceCompany(insuranceCompany)
                            .build();
                    return policyRepository.save(newPolicy);
                });

        extractedFeeStatement.setPolicy(policy);
        feeStatementRepository.save(extractedFeeStatement);
    }
}
