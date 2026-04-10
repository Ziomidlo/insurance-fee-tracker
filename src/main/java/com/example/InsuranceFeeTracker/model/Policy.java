package com.example.InsuranceFeeTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policySeries;

    private String policyNumber;

    @Column(unique = true, nullable = false)
    private String fullPolicyNumber;

    private String insuranceCompany;


}
