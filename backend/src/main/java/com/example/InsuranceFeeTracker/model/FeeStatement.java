package com.example.InsuranceFeeTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fee_statements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String office;
    private LocalDate createdDate;
    private int installment;

    @Column(columnDefinition = "TEXT")
    private String product;

    @Column(columnDefinition = "TEXT")
    private String risk;
    private BigDecimal collection;
    private BigDecimal rate;
    private BigDecimal commissionAmount;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;
}
