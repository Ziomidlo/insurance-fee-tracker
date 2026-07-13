package com.example.InsuranceFeeTracker.dto;

import com.example.InsuranceFeeTracker.model.Enum.PaymentMethod;
import com.example.InsuranceFeeTracker.model.Policy;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SubmittedFormDTO(
        Long id,
        LocalDate confirmedDate,
        BigDecimal collection,
        BigDecimal cash,
        PaymentMethod paymentMethod,
        Policy policy
) {
}
