package com.example.InsuranceFeeTracker;

import com.example.InsuranceFeeTracker.model.Policy;
import com.example.InsuranceFeeTracker.repository.PolicyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PolicyRepositoryTest {
    @Autowired
    private PolicyRepository policyRepository;

    @Test
    void shouldFindPolicyByFullPolicyNumber() {
        Policy policy = Policy.builder()
                .policySeries("KWA")
                .policyNumber("000123")
                .fullPolicyNumber("KWA000123")
                .insuranceCompany("Wiener")
                .build();

        policyRepository.save(policy);

        Optional<Policy> foundPolicy = policyRepository.findByFullPolicyNumber("KWA000123");

        assertThat(foundPolicy).isPresent();
        assertThat(foundPolicy.get().getInsuranceCompany()).isEqualTo("Wiener");
    }
}
