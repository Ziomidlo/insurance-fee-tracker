package com.example.InsuranceFeeTracker.model;

import com.example.InsuranceFeeTracker.model.Enum.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "submitted_forms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmittedForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate confirmedDate;
    private BigDecimal collection;
    private BigDecimal cash;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;


}
