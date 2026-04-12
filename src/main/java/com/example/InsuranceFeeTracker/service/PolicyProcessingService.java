package com.example.InsuranceFeeTracker.service;

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
    public void processSubmittedForm(String extractedPolicySeries, String extractedPolicyNumber, SubmittedForm extractedForm){
        String fullPolicyNumber = extractedPolicySeries + extractedPolicyNumber;

        Policy policy = policyRepository.findByFullPolicyNumber(fullPolicyNumber)
                .orElseGet(() -> {
                    Policy newPolicy = Policy.builder()
                            .policySeries(extractedPolicySeries)
                            .policyNumber(extractedPolicyNumber)
                            .fullPolicyNumber(fullPolicyNumber)
                            .build();
                    return policyRepository.save(newPolicy);
                });

        extractedForm.setPolicy(policy);
        submittedFormRepository.save(extractedForm);

    }
}
